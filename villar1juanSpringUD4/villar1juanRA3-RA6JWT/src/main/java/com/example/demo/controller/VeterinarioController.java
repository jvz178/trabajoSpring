package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@Controller
public class VeterinarioController {

	private String motivoCita;
	private Date fechaCita;
	private Mascotas idMascotaCita;
	private Usuarios idVeterinarioCita;
	private int idCita;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioCita")
	private ServicioCita servicioCita;
	
	@GetMapping("/mostrarMascotas")
	public String tablaMascotas(Model model) {
		
		model.addAttribute("mascotas", servicioMascota.listarMascota());
		return "tablaMascotas";
	}
	
	@GetMapping("/citasDeHoy")
	public String citasDeHoy(Model model) {
		
		Usuarios veterinario = servicioUsuario.getUsuario();
		model.addAttribute("citas",getCitasDeHoyVeterinario(veterinario));
		return "citasDeHoy";
	}
	
	@GetMapping("/citasProxDias")
	public String citasProxDias(Model model) {
		
		Usuarios veterinario = servicioUsuario.getUsuario();
		model.addAttribute("citas",getCitasProxDias(veterinario));
		return "citasProxDias";
	}
	
	@GetMapping("/realizarCita/{id}")
	public String realizarCita(@PathVariable int id, Model model) throws Exception {
		
		Citas cita = servicioCita.obtenerCitaPorId(id);
		motivoCita=cita.getMotivo();
		fechaCita=cita.getFecha();
		idMascotaCita=cita.getIdMascota();
		idVeterinarioCita=cita.getIdVeterinario();
		idCita=cita.getId();
		model.addAttribute("cita", cita);
		return "realizarCita";
	}
	
	@PostMapping("/citaRealizada")
	public String citaRealizada(@ModelAttribute Citas cita) {
		
		cita.setRealizada(true);
		cita.setMotivo(motivoCita);
		cita.setFecha(fechaCita);
		cita.setIdMascota(idMascotaCita);
		cita.setIdVeterinario(idVeterinarioCita);
		cita.setId(idCita);
		servicioCita.actualizarCita(cita);
		return "redirect:/citasDeHoy";
	}
	
    public List<Citas> getCitasDeHoyVeterinario(Usuarios veterinario){
		
		List<Citas> citas = veterinario.getCitasVeterinario();
		List<Citas> citasDeHoy = new ArrayList<Citas>();
		String fechaHoy=LocalDate.now().toString();
		Date fecha = Date.valueOf(fechaHoy);
		
		for(Citas cita : citas) {
			
			if((cita.getFecha().toString().equals(fecha.toString()))==true) {
				System.out.println(cita.getFecha().toString());
				if(cita.getRealizada()==false) {
					
					citasDeHoy.add(cita);
				}
			}
		}
		
		return citasDeHoy;
	}
    
    public List<Citas> getCitasProxDias(Usuarios veterinario){
		
		List<Citas> citas = veterinario.getCitasVeterinario();
		List<Citas> citasDeHoy = new ArrayList<Citas>();
		String fechaHoy=LocalDate.now().toString();
		Date fecha = Date.valueOf(fechaHoy);
		
		for(Citas cita : citas) {
			
			if(cita.getFecha().after(fecha)==true) {
				
				if(cita.getRealizada()==false) {
					
					citasDeHoy.add(cita);
				}
			}
		}
		
		return citasDeHoy;
	}
}
