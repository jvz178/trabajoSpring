package com.example.demo.services;

import java.util.List;


import com.example.demo.entity.Veterinarios;



public interface ServicioVeterinario {
	public abstract List<Veterinarios> listarVeterinarios();
	public abstract Veterinarios añadirVeterinario(Veterinarios veterinario);
	public abstract int quitarVeterinario(int id);
	public abstract Veterinarios actualizarVeterinario(Veterinarios veterinario);
}
