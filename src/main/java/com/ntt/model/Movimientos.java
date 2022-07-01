package com.ntt.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
public class Movimientos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movimientoid;
	
	@Column(name = "tipo_movimiento", length = 50, nullable = false)
	private String tipoMovimiento;
	
	@Column(name = "valor_movimiento", columnDefinition = "Decimal(7,2)", nullable = true)
	private double valorMovimeinto;
	
	@Column(name = "saldo_movimiento", columnDefinition = "Decimal(7,2)", nullable = true)
	private double saldoMovimiento;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	
	@ManyToOne
	@JoinColumn(name="cuenta_id", nullable = false)
	private Cuenta cuenta;
	
	public static final String CREDITO = "CREDITO ";
	
	public static final String DEBITO = "DEBITO ";


	public Movimientos() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Movimientos(int movimientoid, String tipoMovimiento, double valorMovimeinto, double saldoMovimiento,
			LocalDateTime fecha, Cuenta cuenta) {
		super();
		this.movimientoid = movimientoid;
		this.tipoMovimiento = tipoMovimiento;
		this.valorMovimeinto = valorMovimeinto;
		this.saldoMovimiento = saldoMovimiento;
		this.fecha = fecha;
		this.cuenta = cuenta;
	}


	public int getMovimientoid() {
		return movimientoid;
	}


	public void setMovimientoid(int movimientoid) {
		this.movimientoid = movimientoid;
	}


	public String getTipoMovimiento() {
		return tipoMovimiento;
	}


	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}


	public double getValorMovimeinto() {
		return valorMovimeinto;
	}


	public void setValorMovimeinto(double valorMovimeinto) {
		this.valorMovimeinto = valorMovimeinto;
	}


	public double getSaldoMovimiento() {
		return saldoMovimiento;
	}


	public void setSaldoMovimiento(double saldoMovimiento) {
		this.saldoMovimiento = saldoMovimiento;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public Double calcularSaldo() {
		Double saldo = 0.0;
		if(cuenta.getSaldoInicial() > 0) {
			if(this.tipoMovimiento.equals(CREDITO)) {
				saldo = cuenta.getSaldoInicial() + this.valorMovimeinto;
			}
			if(this.tipoMovimiento.equals(DEBITO) && this.valorMovimeinto <= cuenta.getSaldoInicial()) {
				saldo = cuenta.getSaldoInicial() - this.valorMovimeinto;
			}else {
				saldo = 0.0;
			}
		}else {
			saldo = 0.0;
		}
		
		return saldo;
	}
	
	
	
	
	
	
	
	
	
	

}
