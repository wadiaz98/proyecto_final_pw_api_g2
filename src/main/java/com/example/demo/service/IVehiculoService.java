package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.model.Vehiculo;
import com.example.demo.service.dto.VehiculoDTO;
import com.example.demo.service.to.VehiculoTO;


public interface IVehiculoService {
	
	public void ingresar(VehiculoTO vehiculo);
	public void actualizar(VehiculoTO vehiculo);
	public VehiculoTO buscar(String placa);
	public void eliminar(String placa);
	
	public List<VehiculoDTO> buscarVehiculosDisponiblres(String marca, String modelo);
	public List<VehiculoDTO> buscarPorMarca(String marca);
	//EXTRA
	public List<String> buscarMarcasVehiculos();
	public List<VehiculoDTO> obtenerTodos();
	public List<String> obtenerModelosPorMarca(String marca);

}
