package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.model.Reserva;
import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.to.ReservaTO;
import com.example.demo.service.to.RetiroTO;

public interface IReservaService {
	
	public void reservar(String cedula, String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public RetiroTO retirarVehiculoReservado(Integer numero);
	public List<ReservaTO> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
}
