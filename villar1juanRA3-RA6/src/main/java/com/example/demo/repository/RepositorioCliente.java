package com.example.demo.repository;

import java.io.Serializable;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Clientes;



@Repository("repositorioCliente")
public interface RepositorioCliente extends JpaRepository<Clientes, Serializable>{

	public abstract Clientes findByUsername(String username);
}
