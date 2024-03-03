package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepo;
import com.example.demo.repository.model.Cliente;
import com.example.demo.service.dto.ClienteDTO;
import com.example.demo.service.to.ClienteTo;


@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepo clienteRepo;
	@Override
	public void registrarse(ClienteTo cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.insertar(convertToClient(cliente));
	}

	@Override
	public void actualizarDatos(ClienteTo cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.actualizar(convertToClient(cliente));
	}

	//DTO -> ligero
	@Override
	public List<ClienteDTO> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarApellido(apellido);
	}

	@Override
	public ClienteTo buscar(String cedula) {
		// TODO Auto-generated method stub
		return this.convertToClientTO(this.clienteRepo.buscar(cedula));
	}

	@Override
	public List<ClienteTo> reporte() {
		// TODO Auto-generated method stub
		List<Cliente> lista = this.clienteRepo.reporte();
		List<ClienteTo> listaFinal = new ArrayList();
		//listaFinal = lista.forEach(lst -> convertir(lst));
		for(Cliente c : lista) {
			listaFinal.add(this.convertToClientTO(c));
		}
		return listaFinal;
	}

	@Override
	public void eliminar(String cedula) {
		// TODO Auto-generated method stub
		this.clienteRepo.eliminar(cedula);
	}
	
	@Override
	public List<ClienteDTO> obtenerTodos() {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarTodos();
	}	
	
	// FUNCIONES 
	private Cliente convertToClient(ClienteTo clienteTo){
		Cliente cliente = new Cliente();
		cliente.setApellido(clienteTo.getApellido());
		cliente.setCedula(clienteTo.getCedula());
		cliente.setFechaNacimiento(clienteTo.getFechaNacimiento());
		cliente.setGenero(clienteTo.getGenero());
		cliente.setNombre(clienteTo.getNombre());
		cliente.setRegistro(clienteTo.getRegistro());
		cliente.setPassword(clienteTo.getPassword());
		cliente.setTipo(clienteTo.getTipo());
		return cliente;
	}
	
	private ClienteTo convertToClientTO(Cliente cliente){
		ClienteTo clienteTo = new ClienteTo();
		clienteTo.setApellido(cliente.getApellido());
		clienteTo.setCedula(cliente.getCedula());
		clienteTo.setFechaNacimiento(cliente.getFechaNacimiento());
		clienteTo.setGenero(cliente.getGenero());
		clienteTo.setNombre(cliente.getNombre());
		clienteTo.setRegistro(cliente.getRegistro());
		clienteTo.setPassword(cliente.getPassword());
		clienteTo.setTipo(cliente.getTipo());
		return clienteTo;
	}

}
