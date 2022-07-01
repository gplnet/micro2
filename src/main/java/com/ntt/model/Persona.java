package com.ntt.model;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clienteId;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "genero", length = 15, nullable = false)
	private String genero;
	
	@Column(name="edad", nullable=false, length = 3)
	private int edad;
	
	@Column(name = "identificacion", length = 13, nullable = false)
	private String identificacion;
	
	@Column(name = "direccion", length = 100, nullable = false)
	private String direccion;
	
	@Column(name="telefono", nullable=false, length = 50)
	private String telefono;

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

	
	
	

	



	
	
	
	
	
	

}
