package com.example.demo.repository.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cobro")
public class Cobro {
	@GeneratedValue(generator = "seq_cobr",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_cobr",sequenceName = "seq_cobr" ,allocationSize = 1)
	@Id
	@Column(name = "cobr_id")
	private int id;
	@Column(name = "cobr_numero_tarjeta")
	private String numeroTarjeta;
	@Column(name = "cobr_fecha_cobro")
	private LocalDateTime fecha;
	
	@OneToOne(mappedBy = "reserva")
	private Reserva reserva;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
}
