package com.example.demo.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	@Column(name="password", nullable=false)
	@Size(max=150)
	private String password;
	
	@NotNull
	@Size(max=20)
	private String role;
	
	@NotNull
	private boolean activado=false;
	
	@OneToMany(mappedBy="idCliente")
	private List<Mascotas> mascotas = new ArrayList<>();
	
	@OneToMany(mappedBy="idVeterinario")
	private List<Citas> citasVeterinario = new ArrayList<>();
	
	public Usuarios() {}
	
	public Usuarios(int id, String nombre, String apellidos, String telefono, String username, String password, String role, boolean activado) {
		
		this.id=id;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.username=username;
		this.password=password;
		this.role=role;
		this.activado=activado;
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

	public boolean getActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public List<Mascotas> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascotas> mascotas) {
		this.mascotas = mascotas;
	}

	public List<Citas> getCitasVeterinario() {
		return citasVeterinario;
	}

	public void setCitasVeterinario(List<Citas> citasVeterinario) {
		this.citasVeterinario = citasVeterinario;
	}
	
	public List<Citas> getCitasDeHoyVeterinario(){
		
		List<Citas> citas = getCitasVeterinario();
		List<Citas> citasDeHoy = new ArrayList<Citas>();
		String fechaHoy=LocalDate.now().toString();
		Date fecha = Date.valueOf(fechaHoy);
		
		for(Citas cita : citas) {
			
			if((cita.getFecha().toString().equals(fecha.toString()))==false) {
				if(cita.getRealizada()==false) {
					
					citasDeHoy.add(cita);
				}
			}
		}
		
		return citasDeHoy;
	}
}
