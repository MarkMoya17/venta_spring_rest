package com.venta.controladores.web.rest;

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
import com.venta.servicios.ServicioVenta;

@RestController
@RequestMapping("/apiproducto")
public class ProductoRestController {
	@Autowired
	ServicioVenta servicio;
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Producto> findAllProd() {
		
		return servicio.findByEstado(1);
	}
	
	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Producto findOne(@PathVariable Integer id) {
		
		return servicio.findOneProd(id);
	}

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Producto producto) {
		producto.setEstado(1);
		servicio.saveProd(producto);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Producto producto, @PathVariable Integer id) {
		
		Producto p = servicio.findOneProd(id);
		p.setNombre(producto.getNombre());
		p.setStock(producto.getStock());
		p.setPrecio(producto.getPrecio());
		p.setCategoria(producto.getCategoria());
		servicio.saveProd(p);
	}

	@PutMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody Producto producto, @PathVariable Integer id) {
		Producto p = servicio.findOneProd(id);
		p.setEstado(0);
		servicio.saveProd(p);
	}
	
}
