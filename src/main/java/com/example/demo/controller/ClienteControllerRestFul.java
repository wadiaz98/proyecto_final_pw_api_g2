package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.dto.ClienteDTO;
import com.example.demo.service.dto.ReservaDTO;
import com.example.demo.service.dto.VehiculoDTO;
import com.example.demo.service.to.ClienteTo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteControllerRestFul {

	@Autowired
	private IClienteService clienteService;

//	1c. Registrarse como cliente
//	2a. Registrar cliente
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> RegistrarseComoCliente(@RequestBody ClienteTo cliente) {
		this.clienteService.registrarse(cliente);
		return ResponseEntity.status(HttpStatus.OK)
				.body("El cliente " + cliente.getApellido() + " se ha registrado con exito!");
	}

//	1d. Actualizar sus datos de cliente
//  2b. actualizar
	@PutMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizar(@RequestBody ClienteTo cliente, @PathVariable String cedula) { // control
																											// de
																											// excepciones
		ClienteTo tmp = this.clienteService.buscar(cedula);
		cliente.setCedula(tmp.getCedula());
		this.clienteService.actualizarDatos(cliente);
		return ResponseEntity.status(HttpStatus.OK)
				.body("El cliente con identificación " + cedula + " se ha actualizado exitosamente");
	}

//	2b. buscar
	@GetMapping(path = "/{apellido}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<ClienteDTO>> buscarLigero(@PathVariable String apellido) {
		List<ClienteDTO> clientes = this.clienteService.buscarPorApellido(apellido);
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}

//		buscar por cedula
	@GetMapping(path = "/cedula/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ClienteTo> buscarCedula(@PathVariable String cedula) {
		ClienteTo cliente = this.clienteService.buscar(cedula);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

//		buscar todos los clientes
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<ClienteDTO> clientes = this.clienteService.obtenerTodos();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}

//	2b. eliminar
	@DeleteMapping(path = "/{cedula}")
	public ResponseEntity<String> borrar(@PathVariable String cedula) {
		try {
			this.clienteService.eliminar(cedula);
		} catch (DataIntegrityViolationException e) {
			//e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body(
					"El cliente con cedula <<" + cedula + ">> NO se puede eliminar porque tiene registrada una «reserva»");
		} catch (InvalidDataAccessApiUsageException e) {
			//e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK)
					.body("No se puede eliminar la cedula <<" + cedula + ">> porque no existe en la base de datos.");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body("El cliente con identificación " + cedula + " ha sido eliminado");
	}
	
	//EXTRA
	//Login
	@PostMapping(path = "/verificar", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Boolean> verificar(@RequestBody ClienteTo cliente) {
		boolean verificador = this.clienteService.verificador(cliente);
		return ResponseEntity.status(HttpStatus.OK).body(verificador);
	}

}
