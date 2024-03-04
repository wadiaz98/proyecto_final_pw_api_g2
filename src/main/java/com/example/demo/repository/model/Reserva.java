package com.example.demo.repository.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva")
public class Reserva {
	
	@Id
	@SequenceGenerator(name="seq_rese", sequenceName = "seq_rese", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rese")
	@Column(name="rese_numero")
	private Integer numero;
	
	@Column(name="rese_valor_subtotal")
	private BigDecimal valorSubtotal;
	@Column(name="rese_iva")
	private BigDecimal iva;
	@Column(name="rese_valor_total")
	private BigDecimal valorTotal;
	
	@Column(name="rese_estado")
	private String estado;
	
	@Column(name="rese_fecha_inicio")
	private LocalDateTime fechaInicio;
	@Column(name="rese_fecha_fin")
	private LocalDateTime fechaFin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rese_cedula_cliente" )
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rese_placa_vehiculo")
	private Vehiculo vehiculo;

	@Override
	public String toString() {
		return "Reserva [numero=" + numero + ", valorSubtotal=" + valorSubtotal + ", estado=" + estado + ", iva=" + iva
				+ ", valortotal=" + valorTotal + "]";
	}

	//SET Y GET
	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valortotal) {
		this.valorTotal = valortotal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	
	
	
	

}