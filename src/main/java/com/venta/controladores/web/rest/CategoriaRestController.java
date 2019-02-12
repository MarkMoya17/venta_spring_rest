package com.venta.controladores.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venta.proy.Categoria;
import com.venta.servicios.ServicioVenta;

@RestController
@RequestMapping("/apicategoria")
public class CategoriaRestController {
	@Autowired
	ServicioVenta servicio;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Categoria> findAllCategorias() {

		return servicio.findAllCat();
	}

	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Categoria findOne(@PathVariable Integer id) {

		return servicio.findOneCat(id);
	}

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Categoria categoria) {

		servicio.saveCat(categoria);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Categoria categoria, @PathVariable Integer id) {

		Categoria c = servicio.findOneCat(id);
		// informacion que viene de la peticion put
		c.setDenominacion(categoria.getDenominacion());
		servicio.saveCat(c);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {

		servicio.deleteCat(new Categoria(id));
	}
}

