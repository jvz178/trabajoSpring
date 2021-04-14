package com.example.demo.services.impl;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Citas;
import com.example.demo.repository.RepositorioCita;
import com.example.demo.services.ServicioCita;

@Service("servicioCita")
public class ImplServicioCita implements ServicioCita {

	@Autowired
	@Qualifier("repositorioCita")
	private RepositorioCita repositorioCita;

	public List<Citas> listarCita() {
		return repositorioCita.findAll();
	}

	public Citas añadirCita(Citas cita) {
		return repositorioCita.save(cita);
	}

	public int quitarCita(int id) {
		repositorioCita.deleteById(id);
		return 0;
	}

	public Citas actualizarCita(Citas cita) {
		return repositorioCita.save(cita);
	}

}
