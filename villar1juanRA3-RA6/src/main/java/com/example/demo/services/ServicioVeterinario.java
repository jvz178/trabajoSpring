package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Veterinario;



public interface ServicioVeterinario {
	public abstract List<Veterinario> listarVeterinarios();
	public abstract Veterinario añadirVeterinario(Veterinario veterinario);
	public abstract int quitarVeterinario(int id);
	public abstract Veterinario actualizarVeterinario(Veterinario veterinario);
}
