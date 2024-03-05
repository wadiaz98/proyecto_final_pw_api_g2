package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.model.Cobro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CobroRespositoryImpl implements ICobroRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void insertar(Cobro cobro) {
		this.entityManager.persist(cobro);
	}

	@Override
	public Cobro buscar(Integer cobro) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cobro.class, cobro);
	}

}
