package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="citas")
public class Citas {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Size(max=11)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idMascota", referencedColumnName= "id")
	private Mascotas idMascota;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idVeterinario", referencedColumnName= "id")
	private Usuarios idVeterinario;
	
	@NotNull
	private Date fecha;
	
	@NotNull
	@Size(max=100)
	private String motivo;
	
	private String informe;
	
	@NotNull
	private boolean realizada;
	
	public Citas() {}
	
	public Citas(int id, Mascotas idMascota, Usuarios idVeterinario, Date fecha, String motivo, String informe, boolean realizada) {
		
		this.id=id;
		this.idMascota=idMascota;
		this.idVeterinario=idVeterinario;
		this.fecha=fecha;
		this.motivo=motivo;
		this.informe=informe;
		this.realizada=realizada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mascotas getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(Mascotas idMascota) {
		this.idMascota = idMascota;
	}

	public Usuarios getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(Usuarios idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public boolean getRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
	
}
