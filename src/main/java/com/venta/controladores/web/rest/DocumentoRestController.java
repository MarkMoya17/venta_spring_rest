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

import com.venta.proy.Documento;
import com.venta.servicios.ServicioVenta;

@RestController
@RequestMapping("/apidocumento")
public class DocumentoRestController {
	@Autowired
	ServicioVenta servicio;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Documento> findAllDocumentos() {
		return servicio.findAllDoc();
	}

	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Documento findOne(@PathVariable Integer id) {

		return servicio.findOneDoc(id);
	}

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Documento documento) {

		servicio.saveDoc(documento);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Documento documento, @PathVariable Integer id) {

		Documento c = servicio.findOneDoc(id);
		// informacion que viene de la peticion put
		c.setNombre(documento.getNombre());
		servicio.saveDoc(c);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {

		servicio.deleteDoc(new Documento(id));
	}
}
