package com.example.demo.controller;

import java.util.List;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioUsuario;

@Controller
public class InicioController {

	private static final String VISTA_REGISTRO="registrarse";
	private static final String VISTA_INICIO="iniciarSesion";
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/")
	public String inicio() {
		
		return "inicio";
	}
	
	@GetMapping("/auth/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("usuario", new Usuarios());
		return VISTA_REGISTRO;
	}
	
	@PostMapping("/auth/nuevoCliente")
	public String nuevoCliente(@ModelAttribute Usuarios usuario, RedirectAttributes flash) {
		
		servicioUsuario.registrar(usuario);
		flash.addFlashAttribute("success", "Usuario registrado");
		return "redirect:/auth/login";
	}
	
}
