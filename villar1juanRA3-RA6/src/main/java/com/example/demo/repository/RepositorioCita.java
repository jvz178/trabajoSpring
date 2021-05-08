package com.example.demo.repository;

import java.io.Serializable;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;

@Repository("repositorioCita")
public interface RepositorioCita extends JpaRepository<Citas, Serializable>{

	public abstract Citas findByIdMascota(Mascotas mascota);
}
