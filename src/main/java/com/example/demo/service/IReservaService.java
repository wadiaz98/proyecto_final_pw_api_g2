package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.model.Reserva;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;

public interface IReservaService {
	
	public BigDecimal consultarReserva(String cedula, String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	public void reservar(ReservaTO reserva);
	public Reserva retirarVehiculoReservado(Integer numero);
	public List<ReporteTO> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
}
