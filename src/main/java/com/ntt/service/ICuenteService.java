package com.ntt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ntt.model.Cuenta;



public interface ICuenteService {
	
	Cuenta registrar(Cuenta cuenta);
	int modificar(Cuenta cuenta);
	void eliminar(Integer id);
	Cuenta listarId(int id);
	List<Cuenta> listar();
	Page<Cuenta> listAllByPage(Pageable pageable);
	
	Cuenta getCuentaByNumber(String cuenta);
	

}
