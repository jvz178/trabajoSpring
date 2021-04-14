package com.example.demo.services;

import java.util.List;


import com.example.demo.entity.Clientes;



public interface ServicioCliente {
	public abstract List<Clientes> listarCliente();
	public abstract Clientes nuevoCliente(Clientes cliente);
	public abstract int quitarCliente(int id);
	public abstract Clientes actualizarCliente(Clientes cliente);
}
