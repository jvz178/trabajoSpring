package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Clientes;
import com.example.demo.repository.RepositorioCliente;
import com.example.demo.services.ServicioCliente;

@Service("servicioCliente")
public class ImplServicioCliente implements UserDetailsService, ServicioCliente{

	@Autowired
	@Qualifier("repositorioCliente")
	private RepositorioCliente repositorioCliente;
	
	public List<Clientes> listarCliente() {
		return repositorioCliente.findAll();
	}

	public Clientes nuevoCliente(Clientes cliente) {
		return repositorioCliente.save(cliente);
	}

	public int quitarCliente(int id) {
		repositorioCliente.deleteById(id);
		return 0;
	}

	public Clientes actualizarCliente(Clientes cliente) {
		return repositorioCliente.save(cliente);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		com.example.demo.entity.Clientes cliente = repositorioCliente.findByUsername(username);
		
		UserBuilder builder = null;
		
		if(cliente != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(cliente.getPassword());
			builder.authorities(new SimpleGrantedAuthority(cliente.getTipo()));
		}
		else {
			
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public com.example.demo.entity.Clientes registrar(com.example.demo.entity.Clientes cliente){
		
		cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
		cliente.setActivado(true);
		
		return repositorioCliente.save(cliente);
	}
}
