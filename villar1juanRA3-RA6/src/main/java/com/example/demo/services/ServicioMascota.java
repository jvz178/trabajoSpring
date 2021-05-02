package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;



public interface ServicioMascota {
	public abstract List<Mascotas> listarMascota();
	public abstract Mascotas añadirMascota(Mascotas mascota);
	public abstract int quitarMascota(int id);
	public abstract Mascotas actualizarMascota(Mascotas mascota);
	public abstract Mascotas obtenerMascotaPorId(int id) throws Exception;
	public abstract Mascotas obtenerMascotasPorIdCliente(Usuarios idCliente);
}
