package com.example.demo.service.to;

import java.time.LocalDateTime;

import com.example.demo.repository.model.Reserva;

public class CobroTO {
	private String numeroTarjeta;
	private Reserva reserva;
	private LocalDateTime fecha;
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	

}
