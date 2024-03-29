package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;
import com.example.demo.service.to.RetiroTO;

public interface IReservaService {
	

	public BigDecimal consultarValorReserva(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	public LocalDateTime consultarReserva(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	public void reservar(ReservaDTO reserva);
	public String retirarVehiculoReservado(Integer numero);
	public List<ReporteTO> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	public ReservaTO buscarPorClienteFecha(String cedula, String placa, LocalDateTime fechaInicio);
	public RetiroTO obtener(Integer numero);

	
}
