package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.model.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReservaRepoImpl implements IReservaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	public void actualizar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public Reserva buscar(Integer numero) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, numero);
	}

	@Override
	public void eliminar(Integer numero) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(numero));
	}

	@Override
	public List<Reserva> buscarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> query = this.entityManager.createQuery(
				"SELECT r FROM Reserva r LEFT JOIN r.cliente c WHERE r.fechaInicio BETWEEN :datoInicio AND :datoFinal",
				Reserva.class);
		query.setParameter("datoInicio", fechaInicio);
		query.setParameter("datoFinal", fechaFin);
		return query.getResultList();
	}

	@Override
	public Reserva buscarPorClienteFecha(String cedula, String placa, LocalDateTime fechaInicio) {
		
		TypedQuery<Reserva> query = this.entityManager.createQuery(
				 "SELECT r FROM Reserva r JOIN r.cliente c JOIN r.vehiculo v WHERE c.cedula = :datoCedula AND v.placa = :datoPlaca AND r.fechaInicio = :datoFecha",
				Reserva.class);
		query.setParameter("datoCedula", cedula);
		query.setParameter("datoPlaca", placa);
		query.setParameter("datoFecha", fechaInicio);
		return query.getSingleResult();
	}

}
