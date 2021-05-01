package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Usuarios;

@Controller
public class LoginController {
	
	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		
		model.addAttribute("usuario", new Usuarios());
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		return "login";
	}
	
	@GetMapping("/logueado")
	public String logueado() throws Exception {
		return "logueado";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
}
