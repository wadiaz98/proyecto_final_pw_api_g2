package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ICobroRepository;
import com.example.demo.repository.model.Cobro;
import com.example.demo.service.to.CobroTO;

@Service
public class CobroServiceImpl implements ICobroService {

	@Autowired
	ICobroRepository cobroRepository;
	
	@Override
	public void guardar(CobroTO cobro) {
		// TODO Auto-generated method stub
		this.cobroRepository.insertar(this.convetir(cobro));
	}
	
	private Cobro convetir(CobroTO cobro) {
		Cobro result=new Cobro();
		result.setFecha(cobro.getFecha());
		result.setNumeroTarjeta(cobro.getNumeroTarjeta());
		result.setReserva(cobro.getReserva());
		return result;
	}

}
