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

import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioUsuario;

@Controller
public class AdminController {

	private static final String VISTA_EDITAR="editarUsuarioAdmin";
	private int idEditar;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/mostrarUsuarios")
	public ModelAndView tablaAdmin() {
		
		ModelAndView mav = new ModelAndView("tablaAdmin");
		mav.addObject("usuarios", servicioUsuario.listarUsuario());
		return mav;
	}
	
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(Model model, @PathVariable int id) {
		
		servicioUsuario.quitarUsuario(id);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/editarUsuarioAdmin/{id}")
	public String editarUsuarioAdmin(@PathVariable int id, Model model) throws Exception {
		
		Usuarios usuario= servicioUsuario.obtenerUsuarioPorId(id);
		idEditar=id;
		model.addAttribute("usuario", usuario);
		return VISTA_EDITAR;
	}
	
	@PostMapping("/usuarioCambiado")
	public String usuarioCambiado(@ModelAttribute Usuarios usuario) {
		
		usuario.setId(idEditar);
		servicioUsuario.actualizarUsuario(usuario);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/auth/nuevoUsuarioAdmin")
	public String nuevoUsuarioAdmin(Model model) {
		
		model.addAttribute("usuario", new Usuarios());
		return "nuevoUsuarioAdmin";
	}
	
	@PostMapping("/auth/usuarioRegistrado")
	public String usuarioRegistrado(@ModelAttribute Usuarios usuario) {
		
		servicioUsuario.registrar(usuario);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/activarDesactivarUsuario/{id}")
	public String activarDesactivarUsuario(@PathVariable int id) throws Exception {
		
		Usuarios usuario = servicioUsuario.obtenerUsuarioPorId(id);
		servicioUsuario.activarDesactivarUsuario(usuario);
		servicioUsuario.actualizarUsuario(usuario);
		return "redirect:/mostrarUsuarios";
	}
}
