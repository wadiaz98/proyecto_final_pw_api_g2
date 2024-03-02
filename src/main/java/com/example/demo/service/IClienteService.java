package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.ClienteDTO;
import com.example.demo.service.to.ClienteTo;

public interface IClienteService {
	//buscar por cedula (id)
	public ClienteTo buscar(String cedula);
	public void registrarse(ClienteTo cliente);
	public void actualizarDatos(ClienteTo cliente);

	public void eliminar(String cedula);
	
	//DTO -> ligero
	public List<ClienteDTO> buscarPorApellido(String apellido);
	
	public List<ClienteTo> reporte();


	
}
