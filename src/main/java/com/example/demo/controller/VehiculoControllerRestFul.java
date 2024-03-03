package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.VehiculoDTO;
import com.example.demo.service.to.VehiculoTO;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin
public class VehiculoControllerRestFul {

	@Autowired
	private IVehiculoService iVehiculoService;
	
//	1a. Buscar vehículos disponibles
	@GetMapping(path = "/{marca}&{modelo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiculoDTO>> buscarVehiculos(@PathVariable String marca, @PathVariable String modelo) {
		List<VehiculoDTO> vehiculos = this.iVehiculoService.buscarVehiculosDisponiblres("KIA", "Stonic");		
		return ResponseEntity.status(HttpStatus.OK).body(vehiculos);
	}
	
//	2c. Ingresar un vehículo
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ingresarVehiculo(@RequestBody VehiculoTO vehiculo) {
		this.iVehiculoService.ingresar(vehiculo);
		return ResponseEntity.status(HttpStatus.OK).body("Vehículo con placas "+vehiculo.getPlaca()+" ha sido ingresado con exito!");
	}
	
	// BUSCAR todas las marcas de vehiculos
	@GetMapping(path = "/marcas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> buscarMarcas() {
		List<String> marcas = this.iVehiculoService.buscarMarcasVehiculos();		
		return ResponseEntity.status(HttpStatus.OK).body(marcas);
	}
	
}
