package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepo;
import com.example.demo.repository.IReservaRepo;
import com.example.demo.repository.IVehiculoRepo;
import com.example.demo.repository.model.Cliente;
import com.example.demo.repository.model.Reserva;
import com.example.demo.repository.model.Vehiculo;
import com.example.demo.service.dto.ReservaConsultaDTO;
import com.example.demo.service.to.ReporteTO;
import com.example.demo.service.to.ReservaTO;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepo iReservaRepo;
	@Autowired
	private IClienteRepo clienteRepo;
	@Autowired
	private IVehiculoRepo iVehiculoRepo;
	
	@Override
	public BigDecimal consultarReserva(String cedula, String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Vehiculo vehiculo = this.iVehiculoRepo.buscar(placa);

		BigDecimal res = null;
		int dias = calcularDias(fechaInicio, fechaFin);
		if(!vehiculo.getEstado().equals("ND")) {
			BigDecimal subtotal= vehiculo.getValorDia().multiply(new BigDecimal(dias));
			BigDecimal valorIva= subtotal.multiply(new BigDecimal(0.12));
			BigDecimal total = subtotal.add(valorIva);
			res=total;
		}
		
		return res;
		
		
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
	public List<ReporteTO> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		List<Reserva> listado = this.iReservaRepo.buscarReporte(fechaInicio, fechaFin);
		List<ReporteTO> nuevo = new ArrayList();
		
		for(Reserva res : listado) {
			ReporteTO tmp = new ReporteTO();
			tmp.setNumero(res.getNumero());
			tmp.setSubtotal(res.getValorSubtotal());
			tmp.setEstado(res.getEstado());
			tmp.setIva(res.getIva());
			tmp.setTotal(res.getValorTotal());
			tmp.setCedula(res.getCliente().getCedula());
			tmp.setApellido(res.getCliente().getApellido());
			tmp.setPlaca(res.getVehiculo().getPlaca());
			tmp.setMarca(res.getVehiculo().getMarca());
			tmp.setModelo(res.getVehiculo().getModelo());
			nuevo.add(tmp);			
		}
		return nuevo;
	}
	
	private ReservaTO convertirTO(Reserva reserva) {
		ReservaTO tmp = new ReservaTO();
		tmp.setNumero(reserva.getNumero());
		tmp.setSubtotal(reserva.getValorSubtotal());
		tmp.setIva(reserva.getIva());
		tmp.setTotal(reserva.getValorTotal());
		tmp.setFechaInicio(reserva.getFechaInicio());
		tmp.setFechaFin(reserva.getFechaFin());
		tmp.setCliente(reserva.getCliente());
		tmp.setVehiculo(reserva.getVehiculo());
		return tmp;
	}

	@Override
	public void reservar(ReservaTO reserva) {
		// TODO Auto-generated method stub
		
	}

}
