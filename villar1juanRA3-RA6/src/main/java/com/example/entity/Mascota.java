package com.example.entity;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.w3c.dom.Text;

public class Mascota {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Size(max=11)
	private int id;
	
	@NotNull
	@Size(max=11)
	private int idMascota;
	
	@NotNull
	@Size(max=11)
	private int idVeterinario;
	
	@NotNull
	@Size(max=30)
	private String raza;
	
	@NotNull
	private Date fechaNacimiento;
	
	@NotNull
	@Size(max=100)
	private String motivo;
	
	@NotNull
	private Text informe;
	
	public Mascota() {}
	
	public Mascota(int id, int idMascota, int idVeterinario, String raza, Date fechaNacimiento, String motivo, Text informe) {
		
		this.id=id;
		this.idMascota=idMascota;
		this.idVeterinario=idVeterinario;
		this.raza=raza;
		this.fechaNacimiento=fechaNacimiento;
		this.motivo=motivo;
		this.informe=informe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public int getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Text getInforme() {
		return informe;
	}

	public void setInforme(Text informe) {
		this.informe = informe;
	}

}
