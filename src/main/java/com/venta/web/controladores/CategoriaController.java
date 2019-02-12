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

import com.venta.proy.Categoria;
import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	ServicioVenta servicio;
	
	@RequestMapping("/index")
	public String lista(Model modelo) {
		
		//Envia a la vista es decir a la plantilla todas las Categorias
		modelo.addAttribute("categoria", servicio.findAllCat());
		
		//cat-index.html en la carpeta categoria
		return "categoria/cat-index";
	}
	
	@RequestMapping("/new")
	public String fNuevaCategoria(Model modelo) {
		
		modelo.addAttribute(new Categoria());
		
		return "categoria/cat-new";
	
	}
	
	// ya tenemos el objeto  categoria lleno con los datos del formulario
	@RequestMapping(value="/insertarCategoria",method=RequestMethod.POST)
	public String insertarCategoria( @Valid @ModelAttribute Categoria categoria, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {
			
			return "categoria/cat-new";
		}else {
			servicio.saveCat(categoria);
			modelo.addAttribute("categoria", servicio.findAllCat());
			return "categoria/cat-index";
		}
		
	}
	@RequestMapping("/borrarCategoria")
	public String borrarCategoria(@RequestParam("clave") Integer id, Model modelo) {
		
		servicio.deleteCat(new Categoria(id));
		modelo.addAttribute("categoria", servicio.findAllCat());
		return "categoria/cat-index";

	}
}


