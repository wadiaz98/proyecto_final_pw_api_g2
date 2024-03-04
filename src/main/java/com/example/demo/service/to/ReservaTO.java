package com.example.demo.service.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.repository.model.Cliente;
import com.example.demo.repository.model.Vehiculo;

public class ReservaTO implements Serializable{

	private static final long serialVersionUID = -7935534310864724699L;
	
	private Integer numero;
	private BigDecimal subtotal;
	private String estado;
	private BigDecimal iva;
	private BigDecimal total;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String cliente;
	private String vehiculo;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	
}
