package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@Controller
public class CitasController {

	@Autowired
	@Qualifier("servicioCita")
	private ServicioCita servicioCita;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
			
	@PreAuthorize("hasRole('ROLE_CLI')")
	@GetMapping("/calendarioCitas")
	public String opcionesCita(Model model) {
		
		return "calendarioCitas";
	}
	
	@GetMapping("/historialCitas/{id}")
	public String historialCitas(Model model, @PathVariable int id) throws Exception {
		
		Mascotas mascota = servicioMascota.obtenerMascotaPorId(id);
		
		if(servicioUsuario.seguridadMascotasUsuarios(mascota)==true) {
			
			model.addAttribute("citas", mascota.getCitasMascotaOrdenFecha());
			return "historialCitas";
		}else {
			
			return"";
		}
	}
	
	@GetMapping("/citasPendientes")
	public String citasPendientes(Model model) {
		
		Usuarios cliente = servicioUsuario.getUsuario();
		model.addAttribute("citas", servicioCita.obtenerCitaPendientesPorMascota(cliente.getMascotas()));
		return "citasPendientes";
	}
}
