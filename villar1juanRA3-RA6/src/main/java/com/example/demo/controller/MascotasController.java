package com.example.demo.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@Controller
public class MascotasController {

	private int idEditar, idMascotaCita, idVeterinarioCita;
	private Date fechaCita;
	private Usuarios idCliente;
	private Citas citaPedida;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioCita")
	private ServicioCita servicioCita;
	
	public Usuarios getCliente() {
		
		UserDetails ud = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuarios cliente = servicioUsuario.obtenerPorUsername(ud.getUsername());
		return cliente;
	}
	
	@PreAuthorize("hasRole('ROLE_CLI')")
	@GetMapping("/tablaMascotas")
	public String tablaMascotas(Model model) throws Exception {
		
		Usuarios cliente = getCliente();
		model.addAttribute("mascotas", cliente.getMascotas());
		return "tablaMascotas";
	}
	
	@GetMapping("/borrarMascota/{id}")
	public String borrarMascota(Model model, @PathVariable int id) {
		
		servicioMascota.quitarMascota(id);
		return "redirect:/tablaMascotas";
	}
	
	@GetMapping("/editarMascota/{id}")
	public String editarUsuarioAdmin(@PathVariable int id, Model model) throws Exception {
		
		Mascotas mascota= servicioMascota.obtenerMascotaPorId(id);
		idEditar=id;
		idCliente=mascota.getIdCliente();
		model.addAttribute("mascota", mascota);
		return "editarMascota";
	}
	
	@PostMapping("/mascotaCambiada")
	public String usuarioCambiado(@ModelAttribute Mascotas mascota) {
		
		mascota.setId(idEditar);
		mascota.setIdCliente(idCliente);
		servicioMascota.actualizarMascota(mascota);
		return "redirect:/tablaMascotas";
	}
	
	@GetMapping("/nuevaMascota")
	public String nuevaMascota(Model model) {
		
		model.addAttribute("mascota", new Mascotas());
		return "nuevaMascota";
	}
	
	@PostMapping("/mascotaRegistrada")
	public String mascotaRegistrada(@ModelAttribute Mascotas mascota) {
		
		mascota.setIdCliente(getCliente());
		servicioMascota.añadirMascota(mascota);
		return "logueado";
	}
	
	@GetMapping("/datosCita/{id}")
	public String datosCita(Model model, @PathVariable int id) {
		
		idMascotaCita=id;
		model.addAttribute("cita", new Citas());
		return "datosCita";
	}
	
	@PostMapping("/datosEstablecidos")
	public String datosEstablecidos(@ModelAttribute Citas cita) throws Exception {
		
		cita.setRealizada(false);
		citaPedida=cita;
		return "redirect:/pedirCita";
	}
	
	@GetMapping("/pedirCita")
	public String pedirCita(Model model) {
		System.out.println("FECHA: "+citaPedida.getFecha());
		model.addAttribute("usuarios", servicioUsuario.listarUsuario());
		return "pedirCita";
	}
	
	@GetMapping("/confirmarCita/{id}")
	public String confirmarCita(@PathVariable int id) throws Exception {
		
		idVeterinarioCita=id;
		return "confirmarCita";
	}
	
	@PostMapping("/citaConfirmada")
	public String citaConfirmada() throws Exception {
		
		Mascotas mascota = servicioMascota.obtenerMascotaPorId(idMascotaCita);
		citaPedida.setIdMascota(mascota);
		Usuarios usuario = servicioUsuario.obtenerUsuarioPorId(idVeterinarioCita);
		citaPedida.setIdVeterinario(usuario);
		
		servicioCita.añadirCita(citaPedida);
		return "redirect:/tablaMascotas";
	}
}