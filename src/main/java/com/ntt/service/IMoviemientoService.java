package com.ntt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ntt.model.Movimientos;



public interface IMoviemientoService {
	
	Movimientos registrar(Movimientos movimientos);
	int modificar(Movimientos movimientos);
	void eliminar(Integer id);
	Movimientos listarId(int id);
	List<Movimientos> listar();
	Page<Movimientos> listAllByPage(Pageable pageable);
	
	double getTotalDebito(LocalDateTime starDate, LocalDateTime endDate, int codCuenta );
	
	byte[] generarReporte(LocalDateTime startDate, LocalDateTime endDate, String usuario) throws Exception ;

}
