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

import com.example.demo.entity.Clientes;
import com.example.demo.services.ServicioCliente;

@Controller
public class EditarClienteController {

	@Autowired
	@Qualifier("servicioCliente")
	private ServicioCliente servicioCliente;
	
	@GetMapping("/auth/editarCliente/{id}")
	public String editarCliente(Model model, @PathVariable(name="id")int id) throws Exception{
		
		Clientes cliente = servicioCliente.obtenerClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "editarCliente";
	}
	
	@PostMapping("/auth/clienteEditado")
	public String clienteEditado(@ModelAttribute Clientes cliente, RedirectAttributes flash) {
		
		servicioCliente.actualizarCliente(cliente);
		flash.addFlashAttribute("success", "Usuario editado");
		return "redirect:/auth/editarCliente";
	}
}
