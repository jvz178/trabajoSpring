package com.example.demo.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mascota;
import com.example.demo.repository.RepositorioMascota;
import com.example.demo.services.ServicioMascota;

@Service("servicioMascota")
public class ImplServicioMascota implements ServicioMascota{

	@Autowired
	@Qualifier("repositorioMascota")
	private RepositorioMascota repositorioMascota;
	
	@Override
	public List<Mascota> listarMascota() {
		// TODO Auto-generated method stub
		return repositorioMascota.findAll();
	}

	@Override
	public Mascota añadirMascota(Mascota mascota) {
		// TODO Auto-generated method stub
		return repositorioMascota.save(mascota);
	}

	@Override
	public int quitarMascota(int id) {
		// TODO Auto-generated method stub
		repositorioMascota.deleteById(id);
		return 0;
	}

	@Override
	public Mascota actualizarMascota(Mascota mascota) {
		// TODO Auto-generated method stub
		return repositorioMascota.save(mascota);
	}

}
