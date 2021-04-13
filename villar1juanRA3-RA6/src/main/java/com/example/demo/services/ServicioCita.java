package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Cita;



public interface ServicioCita {
	public abstract List<Cita> listarCita();
	public abstract Cita añadirCita(Cita cita);
	public abstract int quitarCita(int id);
	public abstract Cita actualizarCita(Cita cita);
}
