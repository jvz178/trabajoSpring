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

import com.example.demo.entity.Clientes;
import com.example.demo.services.ServicioCliente;

@Controller
public class InicioController {

	private static final String VISTA_REGISTRO="registrarse";
	private static final String VISTA_INICIO="iniciarSesion";
	
	@Autowired
	@Qualifier("servicioCliente")
	private ServicioCliente servicioCliente;
	
	@GetMapping("/")
	public String inicio() {
		
		return "inicio";
	}
	
	@GetMapping("/auth/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("cliente", new Clientes());
		return VISTA_REGISTRO;
	}
	
	@PostMapping("/auth/nuevoCliente")
	public String nuevoCliente(@ModelAttribute Clientes cliente) {
		
		servicioCliente.registrar(cliente);
		return "redirect:/auth/login";
	}
	
}
