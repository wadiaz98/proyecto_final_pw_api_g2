package com.example.demo.service.to;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;

public class VehiculoTO extends RepresentationModel<VehiculoTO> implements Serializable {

	private static final long serialVersionUID = 4923715170076797824L;
	
	private String placa;
	private String modelo;	
	private String marca;	
	private String anio;	
	private String pais;	
	private String cilindraje;	
	private BigDecimal avaluo;	
	private BigDecimal valorDia;
	private String estado;
	
	// GET Y SET
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}
	public BigDecimal getAvaluo() {
		return avaluo;
	}
	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
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
