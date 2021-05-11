package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;

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
		model.addAttribute("citas",veterinario.getCitasDeHoyVeterinario());
		return "citasDeHoy";
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
}
