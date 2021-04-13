package com.example.demo.services.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cita;
import com.example.demo.repository.RepositorioCita;
import com.example.demo.services.ServicioCita;

@Service("servicioCita")
public class ImplServicioCita implements ServicioCita {

	@Autowired
	@Qualifier("repositorioCita")
	private RepositorioCita repositorioCita;

	public List<Cita> listarCita() {
		return repositorioCita.findAll();
	}

	public Cita añadirCita(Cita cita) {
		return repositorioCita.save(cita);
	}

	public int quitarCita(int id) {
		repositorioCita.deleteById(id);
		return 0;
	}

	public Cita actualizarCita(Cita cita) {
		return repositorioCita.save(cita);
	}

}
