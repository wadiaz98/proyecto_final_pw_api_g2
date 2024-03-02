package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.model.Cliente;
import com.example.demo.controller.model.Reserva;
import com.example.demo.controller.model.Vehiculo;
import com.example.demo.repository.IClienteRepo;
import com.example.demo.repository.IReservaRepo;
import com.example.demo.repository.IVehiculoRepo;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepo iReservaRepo;
	@Autowired
	private IClienteRepo clienteRepo;
	@Autowired
	private IVehiculoRepo iVehiculoRepo;
	
	@Override
	public void reservar(String cedula, String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		Reserva reserva = new Reserva();
		Cliente cliente= this.clienteRepo.buscar(cedula);
		Vehiculo vehiculo = this.iVehiculoRepo.buscar(placa);
		
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		if(!vehiculo.getEstado().equals("ND")) {
			//cambio de estado del vehiculo (NO DISPONIBLE)
			vehiculo.setEstado("ND");
			this.iVehiculoRepo.actualizar(vehiculo);
			
			reserva.setCliente(cliente);
			reserva.setVehiculo(vehiculo);
			// calculo subtotal
			BigDecimal subtotal= vehiculo.getValorDia().multiply(new BigDecimal(calcularDias(fechaInicio, fechaFin)));
			reserva.setValorSubtotal(subtotal);
			// calculo iva
			BigDecimal valorIva= subtotal.multiply(new BigDecimal(0.12));
			reserva.setIva(valorIva);
			// calculo total
			reserva.setValorTotal(subtotal.add(valorIva));
			// reserva Generada (G) 
			reserva.setEstado("G");
			this.iReservaRepo.insertar(reserva);
		}else {
			System.out.println("Ya existe una reserva, el vehículo no se encuentra disponible !");
		}
		
		
	}
	
	public Integer calcularDias(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Integer numDia =0;
		if(fechaFin.getYear()> fechaInicio.getYear()) {
				if(fechaFin.getYear()%4==0) {// bisiesto
					numDia += (366-fechaFin.getYear()+1);
				}else if(fechaInicio.getYear()%4==0){
					numDia += (366-fechaInicio.getYear()+1);
				}else if(fechaFin.getYear()%4!=0){ // no bisiesto
					numDia += (365-fechaFin.getYear()+1);
				}else {
					numDia += (365-fechaFin.getYear()+1);
			}
			numDia = fechaFin.getDayOfYear();
		}else{
		numDia = Math.abs((fechaFin.getDayOfYear() - fechaInicio.getDayOfYear()) + 1);
		}
		return numDia;
	}

	@Override
	public Reserva retirarVehiculoReservado(Integer numero) {
		// TODO Auto-generated method stub
		Reserva reserva = this.iReservaRepo.buscar(numero);
		if(reserva.getEstado().equals("G")) {
			//VEHICULO: Cambio de estado(No disponible "ND")
			Vehiculo vehiculo= reserva.getVehiculo();
			vehiculo.setEstado("ND"); // D -> ND
			this.iVehiculoRepo.actualizar(vehiculo);
			
			//RESERVA: Cambio de estado(En ejecucion "E")
			reserva.setEstado("E"); // G -> E
			this.iReservaRepo.actualizar(reserva);
		}else {
			System.out.println("El vehiculo no ha sido reservado");
		}
		
		return reserva;
	}

	@Override
	public List<Reserva> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarReporte(fechaInicio, fechaFin);
	}

}
