package com.venta.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	ServicioVenta servicio;
	
	@RequestMapping("/index")
	public String listame(Model modelo) {

		// Envia a la vista es decir a la plantilla todos los productos
//		modelo.addAttribute("productos", servicio.findAllProd());

		// prod-index.html en la carpeta producto
		return "factura/fact-index";
	}
	
	@RequestMapping("/reporte")
	public String reporte(Model modelo) {
		
		return "factura/fact-reporte";
		
	}
	
	
	
	
	
	
}
