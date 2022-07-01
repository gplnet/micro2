package com.ntt.util;

import java.time.LocalDateTime;

public class MovimientoReporte {
	
	private String fecha;
	private String cliente;
	private String numero_cuenta;
	private String tipo;
	private double saldo_inicial;
	private Boolean estado;
	private String movimiento;	
	private double saldo_disponible;
	
	
	
	
	public MovimientoReporte(String fecha, String cliente, String numero_cuenta, String tipo, double saldo_inicial,
			Boolean estado, String movimiento, double saldo_disponible) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.numero_cuenta = numero_cuenta;
		this.tipo = tipo;
		this.saldo_inicial = saldo_inicial;
		this.estado = estado;
		this.movimiento = movimiento;
		this.saldo_disponible = saldo_disponible;
	}
	
	
	public MovimientoReporte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getSaldo_inicial() {
		return saldo_inicial;
	}
	public void setSaldo_inicial(double saldo_inicial) {
		this.saldo_inicial = saldo_inicial;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public double getSaldo_disponible() {
		return saldo_disponible;
	}
	public void setSaldo_disponible(double saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}
	
	

}
