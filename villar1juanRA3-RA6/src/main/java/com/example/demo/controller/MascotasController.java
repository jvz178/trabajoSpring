package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.example.demo.services.ServicioMascota;

@Controller
public class MascotasController {

	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascotas;
}
