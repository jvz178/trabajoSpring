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

import com.example.demo.entity.Clientes;
import com.example.demo.services.ServicioCliente;

@Controller
public class AdminController {

	private static final String VISTA_EDITAR="editarUsuarioAdmin";
	private int idEditar;
	
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
	public String borrarUsuario(Model model, @PathVariable int id) {
		
		servicioCliente.quitarCliente(id);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/editarUsuarioAdmin/{id}")
	public String editarUsuarioAdmin(@PathVariable int id, Model model) throws Exception {
		
		Clientes cliente= servicioCliente.obtenerClientePorId(id);
		idEditar=id;
		model.addAttribute("cliente", cliente);
		return VISTA_EDITAR;
	}
	
	@PostMapping("/usuarioCambiado")
	public String usuarioCambiado(@ModelAttribute Clientes cliente) {
		
		cliente.setId(idEditar);
		servicioCliente.actualizarCliente(cliente);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/auth/nuevoUsuarioAdmin")
	public String nuevoUsuarioAdmin(Model model) {
		
		model.addAttribute("cliente", new Clientes());
		return "nuevoUsuarioAdmin";
	}
	
	@PostMapping("/auth/usuarioRegistrado")
	public String usuarioRegistrado(@ModelAttribute Clientes cliente) {
		
		servicioCliente.registrar(cliente);
		return "redirect:/mostrarUsuarios";
	}
	
	@GetMapping("/activarDesactivarUsuario/{id}")
	public String activarDesactivarUsuario(@PathVariable int id) throws Exception {
		
		Clientes cliente = servicioCliente.obtenerClientePorId(id);
		servicioCliente.activarDesactivarCliente(cliente);
		servicioCliente.actualizarCliente(cliente);
		return "redirect:/mostrarUsuarios";
	}
}
