package com.venta.controladores.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venta.proy.Producto;
import com.venta.proy.Detalle;
import com.venta.proy.Factura;
import com.venta.servicios.ServicioVenta;



@RestController
@RequestMapping("/apifactura")
public class FacturaRestController {
	@Autowired
	ServicioVenta servicio;
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Factura> findAllFact() {
		return servicio.findAllFact();
	}
	
	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Factura findOne(@PathVariable Integer id) {
		return servicio.findOneFact(id);
	}
	
	@GetMapping(value = "/finddetalle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Detalle> findOneDetalle(@PathVariable Integer id) {
		Factura f = servicio.findOneFact(id);
		return f.getDetalles();
	}

	@PostMapping(value = "/savecabecera", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Factura savecabecera(@RequestBody Factura factura) {
		
		servicio.saveFact(factura);
		return factura;

	}
	
	@PutMapping(value = "/savedetalle/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Detalle> savedetalle(@RequestBody List<Detalle> detalles, @PathVariable Integer id) {
		Factura factura = servicio.findOneFact(id);
		
		for (Detalle detalle : detalles) {
			detalle.setFactura(factura);
			servicio.saveDet(detalle);
		}
		return detalles;

	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Factura factura, @PathVariable Integer id) {
		
		Factura p = servicio.findOneFact(id);
		p.setEstado(factura.getEstado());
		servicio.saveFact(p);
	}
	
	@PutMapping(value = "/darbaja/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void darbaja(@RequestBody Factura factura, @PathVariable Integer id) {
		
		Factura p = servicio.findOneFact(id);
		p.setEstado(0);
		servicio.saveFact(p);
	}
	@PutMapping(value = "/daralta/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void daralta(@RequestBody Factura factura, @PathVariable Integer id) {
		
		Factura p = servicio.findOneFact(id);
		p.setEstado(1);
		servicio.saveFact(p);
	}
	
}
