package com.example.demo.services;

import java.util.List;


import com.example.demo.entity.Citas;



public interface ServicioCita {
	public abstract List<Citas> listarCita();
	public abstract Citas añadirCita(Citas cita);
	public abstract int quitarCita(int id);
	public abstract Citas actualizarCita(Citas cita);
}
