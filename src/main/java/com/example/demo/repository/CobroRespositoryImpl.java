package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.model.Cobro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
	public Cobro buscar(Integer idReserva) {
		TypedQuery<Cobro> query = this.entityManager.createQuery("Select c From Cobro c Join c.reserva r where r.id=:datoId", Cobro.class);
		query.setParameter("datoId", idReserva);
		return query.getSingleResult();
	}

}
