package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.w3c.dom.Text;

@Entity
@Table(name="mascotas")
public class Mascotas {

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
    private Date fechaNacimiento;
    
    @NotNull
    @Size(max=100)
    private String foto;
    
    @NotNull
    @Size(max=11)
    private int idCliente;
    
    public Mascotas() {}
    
    public Mascotas(int id, String nombre, String tipo, String raza, Date fechaNacimiento, String foto, int idCliente) {
    	
    	this.id=id;
    	this.nombre=nombre;
    	this.tipo=tipo;
    	this.raza=raza;
    	this.fechaNacimiento=fechaNacimiento;
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
    	return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fecha) {
    	this.fechaNacimiento = fecha;
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
