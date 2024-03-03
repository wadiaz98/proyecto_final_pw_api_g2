package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.model.Reserva;

public interface IReservaService {
	
	public void reservar(String cedula, String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public Reserva retirarVehiculoReservado(Integer numero);
	public List<Reserva> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
}
