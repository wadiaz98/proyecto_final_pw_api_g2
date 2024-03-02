package com.example.demo.repository;

import java.util.List;

import com.example.demo.controller.model.Cliente;
import com.example.demo.service.dto.ClienteDTO;


public interface IClienteRepo {
	
	public void  insertar(Cliente cliente);
	public void actualizar(Cliente cliente);
	public Cliente buscar(String cedula);
	public void eliminar(String cedula);
	
	public List<ClienteDTO> buscarApellido(String apellido);
	
	public List<Cliente> reporte();
}
