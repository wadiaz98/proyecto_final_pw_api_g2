package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ICobroService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.to.CobroTO;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;
import com.example.demo.service.to.RetiroTO;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaControllerRestFul {

	@Autowired
	private IReservaService iReservaService;
	@Autowired
	private IVehiculoService iVehiculoService;
	@Autowired
	private ICobroService iCobroService;

//	1b. Reservar vehículo
//	consultar precio
	@PostMapping(path = "/fecha_disponibilidad", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> consultarDisponibilidad(@RequestBody ReservaDTO reserva) {

		Map<String, Object> responseData = new HashMap<>();
		if (!this.iVehiculoService.buscar(reserva.getPlaca()).getEstado().equals("ND")) {
			responseData.put("disponibilidad", true);
			BigDecimal res = this.iReservaService.consultarValorReserva(reserva.getPlaca(), reserva.getFechaInicio(),
					reserva.getFechaFin());
			responseData.put("valorReserva", res);
		} else {
			responseData.put("disponibilidad", false);
			LocalDateTime res = this.iReservaService.consultarReserva(reserva.getPlaca(), reserva.getFechaInicio(),
					reserva.getFechaFin());
			responseData.put("fechaDisponible", res);
		}

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	// cobro
	@PostMapping(path = "/cobro", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> resgistrarCobro(@RequestBody CobroTO cobro) {
		this.iCobroService.guardar(cobro);
		return ResponseEntity.status(HttpStatus.OK).body("Se ha registrado el cobro de la reserva con exito");
	}

//	reservar
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservaTO> reservar(@RequestBody ReservaDTO reserva) {
		this.iReservaService.reservar(reserva);
		ReservaTO tmp = this.iReservaService.buscarPorClienteFecha(reserva.getCedula(), reserva.getPlaca(),
				reserva.getFechaInicio());
		return ResponseEntity.status(HttpStatus.OK).body(tmp);
	}

//	2e. Retirar un vehículo reservadow
	@GetMapping(path = "/retirar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> retirarVehiculoReservado(@RequestParam Integer numero) {
		String mensaje = this.iReservaService.retirarVehiculoReservado(numero);
		return ResponseEntity.status(HttpStatus.OK).body(mensaje);
	}

//	buscar reporte 
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RetiroTO> obtenerReporte(@RequestParam Integer numero) {
		try {
			RetiroTO retiro = this.iReservaService.obtener(numero);
			return ResponseEntity.status(HttpStatus.OK).body(retiro);
		} catch (NullPointerException e) {
			// e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	// 3a. Reportes de reservas
	@GetMapping(path = "/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReporteTO>> obtenerReportes(@RequestParam LocalDateTime fechaInicio,
			@RequestParam LocalDateTime fechaFin) {
		List<ReporteTO> list = this.iReservaService.reporte(fechaInicio, fechaFin);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
