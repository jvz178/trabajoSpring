package com.example.demo.services.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Veterinarios;
import com.example.demo.repository.RepositorioVeterinario;
import com.example.demo.services.ServicioVeterinario;

@Service("servicioVeterinario")
public class ImplServicioVeterinario implements ServicioVeterinario{

	@Autowired
	@Qualifier("repositorioVeterinario")
	private RepositorioVeterinario repositorioVeterinario;
	
	@Override
	public List<Veterinarios> listarVeterinarios() {
		// TODO Auto-generated method stub
		return repositorioVeterinario.findAll();
	}

	@Override
	public Veterinarios añadirVeterinario(Veterinarios veterinario) {
		// TODO Auto-generated method stub
		return repositorioVeterinario.save(veterinario);
	}

	@Override
	public int quitarVeterinario(int id) {
		// TODO Auto-generated method stub
		repositorioVeterinario.deleteById(id);
		return 0;
	}

	@Override
	public Veterinarios actualizarVeterinario(Veterinarios veterinario) {
		// TODO Auto-generated method stub
		return repositorioVeterinario.save(veterinario);
	}

}
