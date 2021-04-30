package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

	private int idEditar;
	private Clientes idCliente;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioCliente")
	private ServicioCliente servicioCliente;
	
	@PreAuthorize("hasRole('ROLE_CLI')")
	@GetMapping("/tablaMascotas/{id}")
	public String tablaMascotas(Model model, @PathVariable int id) {
		
		int id2=id;
		model.addAttribute("mascotas", servicioMascota.listarMascota());
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
}
