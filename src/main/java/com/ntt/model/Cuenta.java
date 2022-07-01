package com.ntt.model;

import javax.persistence.*;


@Entity
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cuentaId;
	
	
	@Column(name = "numero_cuenta", length = 9, nullable = false)
	private String numeroCuenta;
	
	@Column(name = "tipo_cuenta", length = 50, nullable = false)
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial", columnDefinition = "Decimal(7,2)", nullable = true)
	private double saldoInicial;
	
	@Column(name="estado_cuenta", length = 50, nullable=false)
	private String estadoCuenta;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable = false)
	private Cliente cliente;
	
	

	public Cuenta() {
		
	}

	
	
	

	public Cuenta(int cuentaId, String numeroCuenta, String tipoCuenta, double saldoInicial, String estadoCuenta,
			Cliente cliente) {
		super();
		this.cuentaId = cuentaId;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estadoCuenta = estadoCuenta;
		this.cliente = cliente;
	}





	public int getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	
	
	

}
