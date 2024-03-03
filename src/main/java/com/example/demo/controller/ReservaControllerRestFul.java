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
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> reservarVehiculo(@RequestBody ReservaDTO reserva) {
		this.iReservaService.reservar(reserva.getCedula(), reserva.getPlaca(), reserva.getFechaInicio(), reserva.getFechaFin());
		return ResponseEntity.status(HttpStatus.OK).body("La reserva del vehiculo "+reserva.getPlaca()+" se ha registrado con exito!");
	}
	
	//3a. Reportes de reservas
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReporteTO>> obtenerReporte(@RequestBody ReporteTO reporte, @RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin){
		List<ReservaTO> list = this.iReservaService.reporte(fechaInicio, fechaFin);
		List<ReporteTO> result = new ArrayList<>();
		for(ReservaTO res : list) {
			ReporteTO tmp = new ReporteTO();
			tmp.setNumero(res.getNumero());
			tmp.setSubtotal(res.getSubtotal());
			tmp.setEstado(res.getEstado());
			tmp.setIva(res.getIva());
			tmp.setTotal(res.getTotal());
			tmp.setCedula(res.getCliente().getCedula());
			tmp.setApellido(res.getCliente().getApellido());
			tmp.setPlaca(res.getVehiculo().getPlaca());
			tmp.setMarca(res.getVehiculo().getMarca());
			tmp.setModelo(res.getVehiculo().getModelo());
			result.add(tmp);			
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
