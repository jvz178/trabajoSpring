package com.example.demo.repository;

import java.io.Serializable;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;


@Repository("repositorioMascota")
public interface RepositorioMascota extends JpaRepository<Mascotas, Serializable>{

	public abstract Mascotas findById(int id);
	public abstract Mascotas findByIdCliente(Usuarios idCliente);
}
