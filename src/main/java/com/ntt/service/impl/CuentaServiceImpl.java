package com.ntt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ntt.dao.ICuentaDAO;
import com.ntt.model.Cuenta;
import com.ntt.service.ICuenteService;

@Service
public class CuentaServiceImpl implements ICuenteService{

	@Autowired
	private ICuentaDAO dao;
	
	@Override
	public Cuenta registrar(Cuenta cuenta) {
		// TODO Auto-generated method stub
		 
		return dao.save(cuenta);
	}

	@Override
	public int modificar(Cuenta cuenta) {
		// TODO Auto-generated method stub
		int rpst = dao.save(cuenta) != null ? cuenta.getCuentaId() : 0;
		return rpst > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Cuenta listarId(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Cuenta> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Page<Cuenta> listAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

	@Override
	public Cuenta getCuentaByNumber(String cuenta) {
		// TODO Auto-generated method stub
		return dao.getCuentaByNumber(cuenta);
	}

}
