package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="veterinarios")
public class Veterinarios {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Size(max=11)
	private int id;
	
	@NotNull
	@Size(max=30)
	private String nombre;
	
	@NotNull
	@Size(max=50)
	private String apellidos;
	
	@NotNull
	@Size(max=10)
	private String telefono;
	
	@Column(name="username", unique=true,nullable=false)
	@Size(max=30)
	private String username;
	
	@NotNull
	@Size(max=40)
	private String password;
	
	@NotNull
	@Size(max=10)
	private String role;
	
	public Veterinarios() {}
	
	public Veterinarios(int id, String nombre, String apellidos, String telefono, String username, String password, String role) {
		
		this.id=id;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.username=username;
		this.password=password;
		this.role=role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
