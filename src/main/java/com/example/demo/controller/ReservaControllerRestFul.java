package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.to.ReporteTO;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaControllerRestFul {
	
	@Autowired
	private IReservaService iReservaService;

//	1b. Reservar vehículo
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> reservarVehiculo(@RequestBody ReservaDTO reserva) {
		this.iReservaService.reservar(reserva.getCedula(), reserva.getPlaca(), reserva.getFechaInicio(), reserva.getFechaFin());
		return ResponseEntity.status(HttpStatus.OK).body("La reserva del vehiculo "+reserva.getPlaca()+" se ha registrado con exito!");
	}
	
	//3a. Reportes de reservas
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReporteTO>> obtenerReporte(@RequestBody ReporteTO reporte, @RequestParam LocalDateTime fInicio, @RequestParam LocalDateTime fFin){
		this.iReservaService.reporte(fInicio, fFin);
		
		return null;
	}
}
