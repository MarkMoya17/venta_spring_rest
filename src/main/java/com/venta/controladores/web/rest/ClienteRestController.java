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

import com.venta.proy.Cliente;
import com.venta.servicios.ServicioVenta;

@RestController
@RequestMapping("/apicliente")
public class ClienteRestController {
	@Autowired
	ServicioVenta servicio;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Cliente> findAllClientes() {

		return servicio.findAllCli();
	}

	@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente findOne(@PathVariable Integer id) {

		return servicio.findOneCli(id);
	}

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Cliente cliente) {

		servicio.saveCli(cliente);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Cliente cliente, @PathVariable Integer id) {

		Cliente c = servicio.findOneCli(id);
		// informacion que viene de la peticion put
		c.setApellidos(cliente.getApellidos());
		c.setNombres(cliente.getNombres());
		c.setDireccion(cliente.getDireccion());
		c.setDni(cliente.getDni());
		servicio.saveCli(c);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {

		servicio.deleteCli(new Cliente(id));
	}
}
