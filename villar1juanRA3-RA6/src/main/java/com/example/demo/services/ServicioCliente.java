package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Cliente;



public interface ServicioCliente {
	public abstract List<Cliente> listarCliente();
	public abstract Cliente añadirCliente(Cliente cliente);
	public abstract int quitarCliente(int id);
	public abstract Cliente actualizarCliente(Cliente cliente);
}
