package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@CrossOrigin(origins = "http://localhost:8100", methods= {RequestMethod.GET,
RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class Rest {

	@Autowired
	@Qualifier("servicioCita")
	private ServicioCita servicioCita;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
		
	@GetMapping("/citas/{username}")
	public ResponseEntity<Object> citas(@PathVariable String username) throws Exception{
		System.out.println("DENTRO");
		Usuarios usuario = servicioUsuario.obtenerPorUsername(username);
		System.out.println(usuario.getNombre());
		List<Mascotas> mascotasUsuario = servicioMascota.obtenerMascotasPorIdCliente(usuario);
		List<Citas> citasUsuario = new ArrayList<>();
		System.out.println("DOSSSSSSSSSSSS");
		for(Mascotas m : mascotasUsuario) {
			
			citasUsuario.addAll(servicioCita.listarCitaPorMascota(m));
		}
		System.out.println("ULTIMO TRAMO");
		for(Citas c : citasUsuario) {
			System.out.println(c.getInforme());
		}
		if(citasUsuario.isEmpty()) {
			
			return ResponseEntity.notFound().build();
		}else {
			
			return ResponseEntity.ok(citasUsuario);
		}
	}
}
