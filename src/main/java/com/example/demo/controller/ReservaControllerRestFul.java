package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.ReservaDTO;

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
}
