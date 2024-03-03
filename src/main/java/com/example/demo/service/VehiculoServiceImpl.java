package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepo;
import com.example.demo.repository.model.Vehiculo;
import com.example.demo.service.dto.VehiculoDTO;
import com.example.demo.service.to.VehiculoTO;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepo iVehiculoRepo;
	
	@Override
	public List<VehiculoDTO> buscarVehiculosDisponiblres(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarMarcaModelo(marca, modelo);
	}

	@Override
	public void ingresar(VehiculoTO vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.insertar(convertToVehiculo(vehiculo));
	}

	@Override
	public List<VehiculoDTO> buscarPorMarca(String marca) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarMarca(marca);
	}

	@Override
	public VehiculoTO buscar(String placa) {
		// TODO Auto-generated method stub
		return convertToVehiculoTO(this.iVehiculoRepo.buscar(placa));
	}

	@Override
	public void eliminar(String placa) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.eliminar(placa);
	}

	@Override
	public void actualizar(VehiculoTO vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.actualizar(convertToVehiculo(vehiculo));
	}
	
	private Vehiculo convertToVehiculo(VehiculoTO vehiculoTO) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setAnio(vehiculoTO.getAnio());
		vehiculo.setAvaluo(vehiculoTO.getAvaluo());
		vehiculo.setCilindraje(vehiculoTO.getCilindraje());
		vehiculo.setEstado(vehiculoTO.getEstado());
		vehiculo.setMarca(vehiculoTO.getMarca());
		vehiculo.setModelo(vehiculoTO.getModelo());
		vehiculo.setPais(vehiculoTO.getPais());
		vehiculo.setPlaca(vehiculoTO.getPlaca());
		vehiculo.setValorDia(vehiculoTO.getValorDia());
		return vehiculo;
	}
	
	private VehiculoTO convertToVehiculoTO(Vehiculo vehiculo) {
		VehiculoTO vehiculoTO = new VehiculoTO();
		vehiculoTO.setAnio(vehiculo.getAnio());
		vehiculoTO.setAvaluo(vehiculo.getAvaluo());
		vehiculoTO.setCilindraje(vehiculo.getCilindraje());
		vehiculoTO.setEstado(vehiculo.getEstado());
		vehiculoTO.setMarca(vehiculo.getMarca());
		vehiculoTO.setModelo(vehiculo.getModelo());
		vehiculoTO.setPais(vehiculo.getPais());
		vehiculoTO.setPlaca(vehiculo.getPlaca());
		vehiculoTO.setValorDia(vehiculo.getValorDia());
		return vehiculoTO;
	}

	@Override
	public List<String> buscarMarcasVehiculos() {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarMarcasVehiculos();
	}

}
