package com.example.demo.services.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.RepositorioCliente;
import com.example.demo.services.ServicioCliente;

@Service("servicioCliente")
public class ImplServicioCliente implements ServicioCliente{

	@Autowired
	@Qualifier("repositorioCliente")
	private RepositorioCliente repositorioCliente;
	
	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		return repositorioCliente.findAll();
	}

	@Override
	public Cliente añadirCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return repositorioCliente.save(cliente);
	}

	@Override
	public int quitarCliente(int id) {
		// TODO Auto-generated method stub
		repositorioCliente.deleteById(id);
		return 0;
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return repositorioCliente.save(cliente);
	}

}
