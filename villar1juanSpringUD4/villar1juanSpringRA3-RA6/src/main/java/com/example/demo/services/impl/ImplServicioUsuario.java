package com.example.demo.services.impl;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositorioUsuario;
import com.example.demo.services.ServicioUsuario;

@Service("servicioUsuario")
public class ImplServicioUsuario implements UserDetailsService, ServicioUsuario{

	@Autowired
	@Qualifier("repositorioUsuario")
	private RepositorioUsuario repositorioUsuario;
	
	public List<Usuarios> listarUsuario() {
		return repositorioUsuario.findAll();
	}

	public Usuarios nuevoUsuario(Usuarios usuario) {
		return repositorioUsuario.save(usuario);
	}

	public int quitarUsuario(int id) {
		repositorioUsuario.deleteById(id);
		return 0;
	}

	public Usuarios actualizarUsuario(Usuarios usuario) {
		return repositorioUsuario.save(usuario);
	}
	
	public Usuarios encontrarPorId(int id) {
		
		return repositorioUsuario.findById(id);
	}
	
	public void activarDesactivarUsuario(Usuarios usuario) {
		
		if(usuario.getActivado()==false) {
			usuario.setActivado(true);
		}else {
			usuario.setActivado(false);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		com.example.demo.entity.Usuarios usuario = repositorioUsuario.findByUsername(username);
		
		UserBuilder builder = null;
		
		if(usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));
		}
		else {
			
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public com.example.demo.entity.Usuarios registrar(com.example.demo.entity.Usuarios usuario){
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setActivado(false);
		usuario.setRole("ROLE_CLI");
		
		return repositorioUsuario.save(usuario);
	}

	@Override
	public Usuarios obtenerUsuarioPorId(int id) throws Exception {
		
		return repositorioUsuario.findById(id);
	}

	@Override
	public Usuarios obtenerPorUsername(String username) {
		
		return repositorioUsuario.findByUsername(username);
	}
	
	@Override
	public Usuarios getUsuario() {
		
		UserDetails ud = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuarios usuario = obtenerPorUsername(ud.getUsername());
		return usuario;
	}

	@Override
	public boolean seguridadMascotasUsuarios(Mascotas mascota) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails detalles = (UserDetails) auth.getPrincipal();
		Usuarios usuario = obtenerPorUsername(detalles.getUsername());
		
		if(mascota.getIdCliente()==usuario) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
}
