package com.example.demo.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaConsultaDTO {
	private Integer id;
	private BigDecimal valorTotal;
	private LocalDateTime fechaCobro;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	
	
}
