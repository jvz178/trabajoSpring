package com.example.demo.controller;

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

import com.example.demo.entity.Clientes;
import com.example.demo.entity.Mascotas;
import com.example.demo.services.ServicioCliente;
import com.example.demo.services.ServicioMascota;

@Controller
public class MascotasController {

	private int idEditar, idMascotaCita;
	private Clientes idCliente;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioCliente")
	private ServicioCliente servicioCliente;
	
	public Clientes getCliente() {
		
		UserDetails ud = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Clientes cliente = servicioCliente.obtenerClientePorUsername(ud.getUsername());
		return cliente;
	}
	
	@PreAuthorize("hasRole('ROLE_CLI')")
	@GetMapping("/tablaMascotas")
	public String tablaMascotas(Model model) throws Exception {
		
		Clientes cliente = getCliente();
		model.addAttribute("mascotas", servicioMascota.obtenerMascotasPorIdCliente(cliente));
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
		return "tablaMascotas";
	}
	
	@GetMapping("/pedirCita/{id}")
	public String pedirCita(Model model, @PathVariable int id) {
		
		idMascotaCita=id;
		model.addAttribute("clientes", servicioCliente.listarCliente());
		return "pedirCita";
	}
}
