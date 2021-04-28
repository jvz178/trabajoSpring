package com.example.demo.services;

import java.util.List;




import com.example.demo.entity.Mascotas;



public interface ServicioMascota {
	public abstract List<Mascotas> listarMascota();
	public abstract Mascotas añadirMascota(Mascotas mascota);
	public abstract int quitarMascota(int id);
	public abstract Mascotas actualizarMascota(Mascotas mascota);
	//Usar countBy para el limite de 3
}
