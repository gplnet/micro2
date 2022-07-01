package com.ntt.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name="clienteId")
public class Cliente extends Persona {
	
	
	private int clienteId;
	
	@Column(name="estado", nullable=false, length = 50)
	private String estado;
	
	@Column(name = "contrasena", length = 6, nullable = false)
	private String contrasena;
	
	
	

	public Cliente() {
		
	}
	
	


	public Cliente(int clienteId, String estado, String contrasena) {
		super();
		this.clienteId = clienteId;
		this.estado = estado;
		this.contrasena = contrasena;
	}









	public int getClienteId() {
		return clienteId;
	}







	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}







	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	

}
