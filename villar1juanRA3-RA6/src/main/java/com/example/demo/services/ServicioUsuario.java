package com.example.demo.services;

import java.util.List;


import com.example.demo.entity.Usuarios;

public interface ServicioUsuario {
	public abstract List<Usuarios> listarUsuario();
	public abstract Usuarios registrar(Usuarios usuario);
	public abstract int quitarUsuario(int id);
	public abstract Usuarios actualizarUsuario(Usuarios usuario);
	public abstract Usuarios obtenerUsuarioPorId(int id) throws Exception;
	public abstract Usuarios obtenerPorUsername(String username);
	public abstract void activarDesactivarUsuario(Usuarios usuario);
}
