package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioMascota;
import com.example.demo.services.ServicioUsuario;

@Controller
public class MascotasController {

	private int idEditar, idMascotaCita, idVeterinarioCita;
	private Date fechaCita;
	private Usuarios idCliente;
	private Citas citaPedida;
	private String fotoMascota;
	
	@Autowired
	@Qualifier("servicioMascota")
	private ServicioMascota servicioMascota;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioCita")
	private ServicioCita servicioCita;
	
	@PreAuthorize("hasRole('ROLE_CLI')")
	@GetMapping("/tablaMascotas")
	public String tablaMascotas(Model model) throws Exception {
		
		Usuarios cliente = servicioUsuario.getUsuario();
		model.addAttribute("mascotas", cliente.getMascotas());
		return "tablaMascotas";
	}
	
	@GetMapping("/borrarMascota/{id}")
	public String borrarMascota(Model model, @PathVariable int id) throws Exception {
		
		Mascotas mascota = servicioMascota.obtenerMascotaPorId(id);
		if(servicioUsuario.seguridadMascotasUsuarios(mascota)==true) {
			
			servicioMascota.quitarMascota(id);
			return "redirect:/tablaMascotas";
		}else {
			
			return "";
		}
	}
	
	@GetMapping("/editarMascota/{id}")
	public String editarMascota(@PathVariable int id, Model model) throws Exception {
		
		Mascotas mascota= servicioMascota.obtenerMascotaPorId(id);
		idEditar=id;
		idCliente=mascota.getIdCliente();
		fotoMascota=mascota.getFoto();
		
		if(servicioUsuario.seguridadMascotasUsuarios(mascota)==true) {
			
			model.addAttribute("mascota", mascota);
			return "editarMascota";
		}else {
			
			return "";
		}
	}
	
	@GetMapping("/cambiarFotoMascota/{id}")
	public String cambiarFotoMascota(@PathVariable int id, Model model) throws Exception {
		
		Mascotas mascota= servicioMascota.obtenerMascotaPorId(id);
		idEditar=id;
		idCliente=mascota.getIdCliente();
		if(servicioUsuario.seguridadMascotasUsuarios(mascota)==true) {
			
			model.addAttribute("mascota", mascota);
			return "cambiarFotoMascota";
		}else {
			
			return "";
		}
	}
	
	@PostMapping("/mascotaCambiada")
	public String mascotaCambiada(@ModelAttribute Mascotas mascota) throws IOException {
					
		mascota.setId(idEditar);
		mascota.setIdCliente(idCliente);
		mascota.setFoto(fotoMascota);
		servicioMascota.actualizarMascota(mascota);
		return "redirect:/tablaMascotas";
	}
	
	@PostMapping("/fotoMascotaCambiada")
	public String fotoMascotaCambiada(@ModelAttribute Mascotas mascota,
			@RequestParam("file") MultipartFile foto) throws IOException {
					
		guardarImagen(mascota,foto);
		mascota.setId(idEditar);
		mascota.setIdCliente(idCliente);
		servicioMascota.actualizarMascota(mascota);
		return "redirect:/tablaMascotas";
	}
	
	@GetMapping("/nuevaMascota")
	public String nuevaMascota(Model model) {
		
		model.addAttribute("mascota", new Mascotas());
		return "nuevaMascota";
	}
	
	@PostMapping("/mascotaRegistrada")
	public String mascotaRegistrada(@ModelAttribute Mascotas mascota,
			@RequestParam("file") MultipartFile foto) throws IOException {
					
		guardarImagen(mascota,foto);
		mascota.setIdCliente(servicioUsuario.getUsuario());
		servicioMascota.añadirMascota(mascota);
		return "logueado";
	}
	
	public void guardarImagen(Mascotas mascota, MultipartFile foto) throws IOException {
		
		if(foto.isEmpty()==false) {
			
			Path imagenes = Paths.get("src//main//resources//static/imagenes/mascotas");
			String rutaAbsoluta = imagenes.toFile().getAbsolutePath();
			
			byte[] bytesImagen = foto.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+foto.getOriginalFilename());
			Files.write(rutaCompleta, bytesImagen);
			
			mascota.setFoto(foto.getOriginalFilename());
		}
	}
	
	@GetMapping("/datosCita/{id}")
	public String datosCita(Model model, @PathVariable int id) throws Exception {
		
		idMascotaCita=id;
		Mascotas mascota = servicioMascota.obtenerMascotaPorId(id);
		if(servicioUsuario.seguridadMascotasUsuarios(mascota)==true) {
			
			model.addAttribute("cita", new Citas());
			return "datosCita";
		}else {
			
			return "";
		}
		
	}
	
	@PostMapping("/datosEstablecidos")
	public String datosEstablecidos(@ModelAttribute Citas cita) throws Exception {
		
		cita.setRealizada(false);
		citaPedida=cita;
		return "redirect:/pedirCita";
	}
	
	@GetMapping("/pedirCita")
	public String pedirCita(Model model) {
		System.out.println("FECHA: "+citaPedida.getFecha());
		model.addAttribute("usuarios", servicioUsuario.listarUsuario());
		return "pedirCita";
	}
	
	@GetMapping("/confirmarCita/{id}")
	public String confirmarCita(@PathVariable int id) throws Exception {
		
		idVeterinarioCita=id;
		return "confirmarCita";
	}
	
	@PostMapping("/citaConfirmada")
	public String citaConfirmada() throws Exception {
		
		Mascotas mascota = servicioMascota.obtenerMascotaPorId(idMascotaCita);
		citaPedida.setIdMascota(mascota);
		Usuarios usuario = servicioUsuario.obtenerUsuarioPorId(idVeterinarioCita);
		citaPedida.setIdVeterinario(usuario);
		
		servicioCita.añadirCita(citaPedida);
		return "redirect:/tablaMascotas";
	}
}
