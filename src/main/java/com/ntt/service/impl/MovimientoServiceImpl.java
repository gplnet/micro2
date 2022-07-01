package com.ntt.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ntt.dao.IMoviemientoDAO;
import com.ntt.model.Movimientos;
import com.ntt.service.IMoviemientoService;
import com.ntt.util.MovimientoReporte;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

@Service
public class MovimientoServiceImpl implements IMoviemientoService {
	
	@Autowired
	private IMoviemientoDAO dao;

	@Override
	public Movimientos registrar(Movimientos movimientos) {
		// TODO Auto-generated method stub
		
		return dao.save(movimientos);
	}

	@Override
	public int modificar(Movimientos movimientos) {
		// TODO Auto-generated method stub
		int resp = dao.save(movimientos) != null ? movimientos.getMovimientoid() : 0;
		return resp > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Movimientos listarId(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<Movimientos> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Page<Movimientos> listAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

	@Override
	public double getTotalDebito(LocalDateTime starDate, LocalDateTime endDate, int codCuenta) {
		// TODO Auto-generated method stub
		return dao.getTotalDebito(starDate, endDate, codCuenta);
	}

	@Override
	public byte[] generarReporte(LocalDateTime startDate, LocalDateTime endDate, String usuario) throws Exception {
		// TODO Auto-generated method stub
		List<Object> listaMov = (List<Object>) dao.findByDateAndUser(startDate, endDate, usuario);
		List<MovimientoReporte> listaReporte =  new ArrayList<>();
		
		Iterator itr = listaMov.iterator();
		while(itr.hasNext()){
			Object[] obj = (Object[]) itr.next();
			
			MovimientoReporte mvr = new MovimientoReporte();
			mvr.setFecha(String.valueOf(obj[0]));
			mvr.setCliente(String.valueOf(obj[1]));
			mvr.setNumero_cuenta(String.valueOf(obj[2]));
			mvr.setTipo(String.valueOf(obj[3]));
			mvr.setSaldo_inicial(((BigDecimal) obj[4]).doubleValue());
			mvr.setEstado((String.valueOf(obj[5]).equals("ACTIVO") ? true : false));
			mvr.setMovimiento(String.valueOf(obj[6]));
			mvr.setSaldo_disponible(((BigDecimal) obj[7]).doubleValue());
			listaReporte.add(mvr);
		}
		
		JRBeanArrayDataSource ItemDataSource = new JRBeanArrayDataSource(listaReporte.toArray());
		Map<String, Object> cabecera = new HashMap<>();
		cabecera.put("ItemDataSource", ItemDataSource);
		
		File file = new ClassPathResource("/reportes/pruebaNtt.jasper").getFile();
		JasperPrint print = JasperFillManager.fillReport(file.getPath(),cabecera, new JREmptyDataSource());
		
		return JasperExportManager.exportReportToPdf(print);
	}

}
