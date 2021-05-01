package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioUsuario;

@Controller
public class EditarClienteController {

	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/auth/editarUsuario/{id}")
	public String editarUsuario(Model model, @PathVariable(name="id")int id) throws Exception{
		
		Usuarios usuario = servicioUsuario.obtenerUsuarioPorId(id);
		model.addAttribute("usuario", usuario);
		return "editarUsuario";
	}
	
	@PostMapping("/auth/clienteEditado")
	public String clienteEditado(@ModelAttribute Usuarios usuario, RedirectAttributes flash) {
		
		servicioUsuario.actualizarUsuario(usuario);
		flash.addFlashAttribute("success", "Usuario editado");
		return "redirect:/auth/editarUsuario";
	}
}
