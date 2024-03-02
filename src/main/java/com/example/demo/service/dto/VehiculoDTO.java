package com.example.demo.service.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;

public class VehiculoDTO {
	
	private String placa;
	private String modelo;
	private String marca;
	private String anio;
	private BigDecimal valorDia;
	private String estado;
	
	public VehiculoDTO() {
		
	}
	
	public VehiculoDTO(String placa, String modelo, String marca, String anio, BigDecimal valorDia, String estado) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anio = anio;
		this.valorDia = valorDia;
		this.estado = estado;
	}

	
	
	@Override
	public String toString() {
		return "VehiculoDTO [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", Año=" + anio
				+ ", valorDia=" + valorDia + ", estado=" + estado + "]";
	}

	//GET Y SET
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public BigDecimal getValorDia() {
		return valorDia;
	}

	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

}
