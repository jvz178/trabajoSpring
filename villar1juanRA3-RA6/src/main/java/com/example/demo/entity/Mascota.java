package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.w3c.dom.Text;

@Entity
public class Mascota {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Size(max=11)
	private int id;
	
    @NotNull
    @Size(max=30)
    private String nombre;
    
    @NotNull
    @Size(max=30)
    private String tipo;
    
    @NotNull
    @Size(max=30)
    private String raza;
    
    @NotNull
    private Date fecha;
    
    @NotNull
    @Size(max=100)
    private String foto;
    
    @NotNull
    @Size(max=11)
    private int idCliente;
    
    public Mascota() {}
    
    public Mascota(int id, String nombre, String tipo, String raza, Date fecha, String foto, int idCliente) {
    	
    	this.id=id;
    	this.nombre=nombre;
    	this.tipo=tipo;
    	this.raza=raza;
    	this.fecha=fecha;
    	this.foto=foto;
    	this.idCliente=idCliente;
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
    
    public String getTipo() {
    	return tipo;
    }
    
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    
    public String getRaza() {
    	return raza;
    }
    
    public void setRaza(String raza) {
    	this.raza = raza;
    }
    
    public Date getFechaNacimiento() {
    	return fecha;
    }
    
    public void setFechaNacimiento(Date fecha) {
    	this.fecha = fecha;
    }
    
    public String getFoto() {
    	return foto;
    }
    
    public void setFoto(String foto) {
    	this.foto = foto;
    }
    
    public int getIdCliente() {
    	return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
    	this.idCliente = idCliente;
    }

}
