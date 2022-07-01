package com.ntt.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.model.Cuenta;
import com.ntt.model.Movimientos;
import com.ntt.service.ICuenteService;
import com.ntt.service.IMoviemientoService;
import com.ntt.util.ErrorMessages;



@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
	
	public static final Double LIMITE = 1000.00;
	public static final Double BASE = 0.00;
	
	@Autowired
	private IMoviemientoService movimientoServ;
	
	@Autowired
	private ICuenteService cuentaService;
	
	@GetMapping( value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Movimientos>> listar(){
		List<Movimientos> mov = new ArrayList<>();
		try {
			mov = movimientoServ.listar();			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Movimientos>>(mov, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrar/{cuenta}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movimientos> registrar(@RequestBody Movimientos mov , @PathVariable("cuenta") String cuenta ) throws Exception{		
		
		Movimientos movimiento = new Movimientos();
		LocalDateTime ldt = LocalDateTime.now();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		try {			
			Cuenta getCuenta =  cuentaService.getCuentaByNumber(cuenta);	
			
			if(getCuenta.getCuentaId() > 0) {
				mov.setCuenta(getCuenta);
				double getLimite = movimientoServ.getTotalDebito(LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00", Locale.ENGLISH).format(ldt), formatter), LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59", Locale.ENGLISH).format(ldt), formatter), mov.getCuenta().getCuentaId());
				
				Double saldoAct = getCuenta.getSaldoInicial();
			
				
				if(saldoAct > mov.getValorMovimeinto()) {
					if(getLimite < LIMITE) {						
						mov.setSaldoMovimiento(saldoAct - mov.getValorMovimeinto());
						mov.setFecha(ldt);
						mov.getCuenta().setSaldoInicial(saldoAct - mov.getValorMovimeinto());
						movimiento = movimientoServ.registrar(mov);
					}else {				
						throw new Exception(ErrorMessages.DAILY_QUOTA_EXCEEDED.getErrorMessage());
						//ErrorMessage errorMesage = new ErrorMessage(new Date(), "lola", "lola");
					}
				}else {
					throw new Exception(ErrorMessages.BALANCE_NOT_AVAILABLE.getErrorMessage());
				}		
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("---> exection:"+e.getMessage());
			return new ResponseEntity<Movimientos>(movimiento ,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return new ResponseEntity<Movimientos>(movimiento, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movimientos> listarId (@PathVariable("id") Integer id){		
		Movimientos account = new Movimientos();
		try {
			account = movimientoServ.listarId(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Movimientos>(account, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Movimientos>(account, HttpStatus.OK);	
		
	}
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Movimientos cuenta) {			
		int resultado = 0;
		try {
			resultado = movimientoServ.modificar(cuenta);			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(@PathVariable Integer id) {
		int resultado = 0;
		try {
			movimientoServ.eliminar(id);
			resultado = 1;
		} catch (Exception e) {
			resultado = 0;
		}

		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Movimientos>> listar(Pageable pageable){
		Page<Movimientos> mov = null;
		try {			
			mov = movimientoServ.listAllByPage(pageable);
			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<Page<Movimientos>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Movimientos>>(mov, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/reportes/{starDate}/{endDate}/{usuario}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(@PathVariable("starDate") String starDate, @PathVariable("endDate") String endDate, @PathVariable("usuario") String usuario) {
		
		byte[] data = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			String path ="C:\\Users\\cberm3o\\Desktop\\reporte";
			LocalDate date = LocalDate.parse(starDate);
			LocalDate dateEnd = LocalDate.parse(endDate);
			
			LocalDateTime beginDate = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00", Locale.ENGLISH).format(date), formatter);
			LocalDateTime afterDate = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59", Locale.ENGLISH).format(dateEnd), formatter);
			
			data = movimientoServ.generarReporte(beginDate, afterDate, usuario);
			 
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	

}
