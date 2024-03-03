package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.model.Vehiculo;
import com.example.demo.service.dto.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public Vehiculo buscar(String placa) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, placa);
	}

	@Override
	public void eliminar(String placa) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(placa));
	}

	@Override
	public List<VehiculoDTO> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoDTO> query = this.entityManager.createQuery(
				"select new com.example.demo.service.dto.VehiculoDTO(v.placa, v.modelo, v.marca, v.anio, v.valorDia, v.estado) from Vehiculo v where v.marca=:datoMarca",
				VehiculoDTO.class);
		query.setParameter("datoMarca", marca);
		return query.getResultList();
	}

	@Override
	public List<VehiculoDTO> buscarMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoDTO> query = this.entityManager.createQuery(
				"SELECT new com.example.demo.service.dto.VehiculoDTO(e.placa, e.modelo, e.marca, e.anio, e.valorDia, e.estado) FROM Vehiculo e WHERE e.marca=:datoMarca AND e.modelo=:datoModelo",
				VehiculoDTO.class);
		query.setParameter("datoMarca", marca);
		query.setParameter("datoModelo", modelo);
		return query.getResultList();
	}

	@Override
	public List<String> buscarMarcasVehiculos() {
		// TODO Auto-generated method stub
		TypedQuery<String> query = this.entityManager.createQuery("SELECT DISTINCT e.marca FROM Vehiculo e", String.class);
		return query.getResultList();
	}

	@Override
	public List<VehiculoDTO> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoDTO> query = this.entityManager.createQuery(
				"SELECT new com.example.demo.service.dto.VehiculoDTO(e.placa, e.modelo, e.marca, e.anio, e.valorDia, e.estado) FROM Vehiculo e",
				VehiculoDTO.class);
		return query.getResultList();
	}

}
