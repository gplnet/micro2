package com.ntt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntt.model.Cuenta;

@Repository
public interface ICuentaDAO extends JpaRepository<Cuenta, Integer>{
	
	
	@Query(value = "select * from cuenta where numero_cuenta = ?1" ,nativeQuery = true)
	public Cuenta getCuentaByNumber(String cuenta);

}
