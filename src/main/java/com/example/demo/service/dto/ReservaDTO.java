package com.example.demo.service.dto;

import java.time.LocalDateTime;

public class ReservaDTO {
	// "1222222", "PDZ-3030", LocalDateTime.of(2020, 03,03,12,12), LocalDateTime.of(2020, 03,10,12,12)
	
	private String cedula;
	private String placa;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
	//SET Y GET
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
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
