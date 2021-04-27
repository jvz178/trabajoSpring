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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.services.ServicioCliente;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("servicioCliente")
	private ServicioCliente servicioCliente;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/mostrarUsuarios")
	public ModelAndView tablaAdmin() {
		
		ModelAndView mav = new ModelAndView("tablaAdmin");
		mav.addObject("clientes", servicioCliente.listarCliente());
		return mav;
	}
	
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(Model model, @PathVariable int id, RedirectAttributes flash) {
		
		servicioCliente.quitarCliente(id);
		flash.addFlashAttribute("success", "Usuario borrado");
		return "redirect:/mostrarUsuarios";
	}
}
