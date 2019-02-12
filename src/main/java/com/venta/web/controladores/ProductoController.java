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

import com.venta.proy.Producto;
import com.venta.servicios.ServicioVenta;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ServicioVenta servicio;

	@RequestMapping("/index")
	public String listame(Model modelo) {

		// Envia a la vista es decir a la plantilla todos los productos
		modelo.addAttribute("productos", servicio.findAllProd());

		// prod-index.html en la carpeta producto
		return "producto/prod-index";
	}

	@RequestMapping("/new")
	public String fnuevoproducto(Model modelo) {
		// Enviamos un objeto producto en blanco para ser llenado y persistido
		modelo.addAttribute(new Producto());
		// Enviamos las categorias para mostrarlas en la lista desplegable
		modelo.addAttribute("categorias", servicio.findAllCat());
		return "producto/prod-new";
	}

	// ya tenemos el producto rellenado con los datos del formulario
	@RequestMapping(value = "/insertarProducto", method = RequestMethod.POST)
	public String insertarProducto(@Valid @ModelAttribute Producto producto, BindingResult validacion, Model modelo) {
		if (validacion.hasErrors()) {

			return "producto/prod-new";
		} else {

			servicio.saveProd(producto);
			modelo.addAttribute("productos", servicio.findAllProd());
			return "producto/prod-index";
		}

	}

	@RequestMapping("/borrarProducto")
	public String borrarProducto(@RequestParam("clave") Integer id, Model modelo) {

		try {
			Producto p = new Producto();
			p = servicio.findOneProd(id);
			System.out.println(p.getNombre());

			servicio.deleteProd(p);
		} catch (Exception e) {
			e.getMessage();
		}

		modelo.addAttribute("productos", servicio.findAllProd());
		return "producto/prod-index";

	}

	@RequestMapping("/fProdNombre")
	public String filtroProductoNombre(@RequestParam("nombre") String nombre, Model modelo) {

		modelo.addAttribute("productos", servicio.findByNombre(nombre));

		return "producto/prod-index";
	}

}
