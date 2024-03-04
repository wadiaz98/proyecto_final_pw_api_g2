package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaControllerRestFul {
	
	@Autowired
	private IReservaService iReservaService;
	@Autowired
	private IClienteService clienteService;

//	1b. Reservar vehículo
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> consltarDisponibilidad(@RequestBody ReservaDTO reserva) {
		BigDecimal res = this.iReservaService.consultarReserva(reserva.getCedula(), reserva.getPlaca(), reserva.getFechaInicio(), reserva.getFechaFin());
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	//3a. Reportes de reservas
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReporteTO>> obtenerReporte(@RequestBody ReporteTO reporte, @RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin){
		List<ReporteTO> list = this.iReservaService.reporte(fechaInicio, fechaFin);
		List<ReporteTO> result = new ArrayList<>();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> reservar(@RequestBody ReservaTO reserva) {
		this.iReservaService.reservar(reserva);
		return ResponseEntity.status(HttpStatus.OK).body("La reserva número: "+reserva.getNumero()+" se a guardado con éxito");
	}
}
