package com.example.demo.services.impl;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Clientes;
import com.example.demo.entity.Mascotas;
import com.example.demo.repository.RepositorioMascota;
import com.example.demo.services.ServicioMascota;

@Service("servicioMascota")
public class ImplServicioMascota implements ServicioMascota{

	@Autowired
	@Qualifier("repositorioMascota")
	private RepositorioMascota repositorioMascota;
	
	@Override
	public List<Mascotas> listarMascota() {
		
		return repositorioMascota.findAll();
	}

	@Override
	public Mascotas añadirMascota(Mascotas mascota) {
		
		return repositorioMascota.save(mascota);
	}

	@Override
	public int quitarMascota(int id) {
		
		repositorioMascota.deleteById(id);
		return 0;
	}

	@Override
	public Mascotas actualizarMascota(Mascotas mascota) {
		
		return repositorioMascota.save(mascota);
	}

	@Override
	public Mascotas obtenerMascotaPorId(int id) throws Exception {
		
		return repositorioMascota.findById(id);
	}

	@Override
	public Mascotas obtenerMascotasPorIdCliente(Clientes idCliente) {
		
		return repositorioMascota.findByIdCliente(idCliente);
	}

}
