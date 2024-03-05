package com.example.demo.service;

import com.example.demo.repository.model.Cobro;
import com.example.demo.service.to.CobroTO;

public interface ICobroService {
	public void guardar(CobroTO cobro);
	
	public Cobro buscar(Integer cobro);
}
