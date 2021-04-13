package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Mascota;



public interface ServicioMascota {
	public abstract List<Mascota> listarMascota();
	public abstract Mascota añadirMascota(Mascota mascota);
	public abstract int quitarMascota(int id);
	public abstract Mascota actualizarMascota(Mascota mascota);
}
