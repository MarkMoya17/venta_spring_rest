package com.venta.web.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.venta.proy.Cliente;
import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ServicioVenta servicio;
	
	@RequestMapping("/index")
	public String lista(Model modelo) {
		
		//Envia a la vista es decir a la plantilla todas los Clientes
		modelo.addAttribute("cliente", servicio.findAllCli());
		
		//cat-index.html en la carpeta cliente
		return "cliente/cliente-index";
	}
	
	@RequestMapping("/new")
	public String fNuevoCliente(Model modelo) {
		
		modelo.addAttribute(new Cliente());
		
		return "cliente/cliente-new";
	
	}
	
	// ya tenemos el objeto  cliente lleno con los datos del formulario
	@RequestMapping(value="/insertarCliente",method=RequestMethod.POST)
	public String insertarCliente( @Valid @ModelAttribute Cliente cliente, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			
			return "cliente/cliente-new";
		}else {
			servicio.saveCli(cliente);
			modelo.addAttribute("cliente", servicio.findAllCli());
			return "cliente/cliente-index";
		}
					
	}
	@RequestMapping("/borrarCliente")
	public String borrarCliente(@RequestParam("clave") Integer id, Model modelo) {
		
		servicio.deleteCli(new Cliente(id));
		modelo.addAttribute("cliente", servicio.findAllCli());
		return "cliente/cliente-index";
		
	}
	
	@RequestMapping("/verCliente")
	public String verCliente(@RequestParam("clave") Integer id, Model modelo) {
						
		modelo.addAttribute("cliente", servicio.findOneCli(id));
		return "cliente/cliente-ver";
		
	}
	
	@RequestMapping("/cargarCliente")
	public String cargarCliente(@RequestParam("clave") Integer id, Model modelo) {
						
		modelo.addAttribute("cliente", servicio.findOneCli(id));
		return "cliente/cliente-editar";
		
	}
	
	@RequestMapping("/editarCliente")
	public String editarCliente( @Valid @ModelAttribute Cliente cliente, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			return "cliente/cliente-edit";
		}else {
			servicio.updateCli(cliente);
			modelo.addAttribute("cliente", servicio.findAllCli());
			return "cliente/cliente-index";
		}
					
	}
	
	
}


