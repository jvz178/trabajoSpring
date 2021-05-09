package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@Controller
public class VeterinarioController {

	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@GetMapping("/mostrarMascotas")
	public String tablaMascotas(Model model) {
		
		model.addAttribute("mascotas", servicioMascota.listarMascota());
		return "tablaMascotas";
	}
	
	@GetMapping("/citasDeHoy")
	public String citasDeHoy(Model model) {
		
		//Date fecha = Date.valueOf("2020-06-07");
		System.out.println(LocalDate.now());
		Usuarios veterinario = servicioUsuario.getUsuario();
		model.addAttribute("citas",veterinario.getCitasDeHoyVeterinario());
		return "citasDeHoy";
	}
}
