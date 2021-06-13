package com.example.demo.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuarios;

@Repository("repositorioUsuario")
public interface RepositorioUsuario extends JpaRepository<Usuarios, Serializable>{

	public abstract Usuarios findByUsername(String username);
	public abstract Usuarios findById(int id);
}
