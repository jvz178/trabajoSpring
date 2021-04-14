package com.example.demo.services.impl;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Clientes;
import com.example.demo.repository.RepositorioCliente;
import com.example.demo.services.ServicioCliente;

@Service("servicioCliente")
public class ImplServicioCliente implements ServicioCliente{

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

}
