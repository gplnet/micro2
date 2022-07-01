package com.ntt.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntt.model.Movimientos;

@Repository
public interface IMoviemientoDAO extends JpaRepository<Movimientos, Integer> {
	
	
	@Query(value="select COALESCE(sum(valor_movimiento),0) as total from movimientos where fecha between ?1 and ?2 and tipo_movimiento='DEBITO'and cuenta_id = ?3", nativeQuery = true)
	public double getTotalDebito(LocalDateTime starDate, LocalDateTime endDate, int codCuenta );
	
	@Query(value = "select movimientos.fecha, persona.nombre, cuenta.numero_cuenta, cuenta.tipo_cuenta, cuenta.saldo_inicial, cuenta.estado_cuenta, movimientos.valor_movimiento, movimientos.saldo_movimiento from movimientos inner join cuenta on cuenta.cuenta_id = movimientos.cuenta_id inner join cliente on cliente.cliente_id = cuenta.cliente_id inner join persona on persona.cliente_id = cliente.cliente_id where persona.nombre like %?3% and movimientos.fecha  between ?1 and ?2 order by movimientos.fecha asc", nativeQuery = true)
	public List<Object> findByDateAndUser(LocalDateTime startDate, LocalDateTime endDate, String usuario);
}
