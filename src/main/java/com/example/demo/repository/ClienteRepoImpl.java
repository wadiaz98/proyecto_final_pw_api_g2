package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.model.Cliente;
import com.example.demo.service.dto.ClienteDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ClienteRepoImpl implements IClienteRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	@Override
	public Cliente buscar(String cedula) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, cedula);
	}

	@Override
	public void eliminar(String cedula) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(cedula));
	}

	@Override
	public List<ClienteDTO> buscarApellido(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<ClienteDTO> query = this.entityManager.createQuery(
				"select new com.example.demo.service.dto.ClienteDTO(c.cedula, c.nombre, c.apellido) from Cliente c where c.apellido=:datoApellido",
				ClienteDTO.class);
		query.setParameter("datoApellido", apellido);
		return query.getResultList();
	}

	@Override
	public List<Cliente> reporte() {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> query = this.entityManager.createQuery(
				"SELECT c FROM Cliente c RIGHT JOIN c.reservas r ORDER BY r.valorTotal DESC", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		for(Cliente c: clientes) {
			c.getReservas().size();
		}
		return clientes;
	}

}
