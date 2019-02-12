package com.venta.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.venta.config.ConfiguracionSpring;

public class SpringWebInicializador implements WebApplicationInitializer{
	//CUANDO SE ARRANQUE LA APLICACION
	public void onStartup(ServletContext contenedor) throws ServletException {
			//CREAR UN CONTEXTO  CON TODAS LAS CLASES REGISTRADAS CON ANOTACIONES DE SPRING 
			AnnotationConfigWebApplicationContext contexto = new AnnotationConfigWebApplicationContext();
			//REGISTRAMOS EL CONTEXTO
			contexto.register(ConfiguracionSpring.class);
			//ENLAZAMOS SPRING CON LA WEB Y ACCEDER AL SERVLET CONTEXT
			contexto.setServletContext(contenedor);
			//REGISTRAMOS EL SERVLET DE FORMA DINAMICA
			ServletRegistration.Dynamic servlet = contenedor.addServlet("dispatcher", new DispatcherServlet(contexto));
			//CARGAR AL INICIAR LA APLICACION
			servlet.setLoadOnStartup(1);
			//MAPEAR TODAS LAS URLS
			servlet.addMapping("/");
	}
}

//SERVLETCONTEXT : OBJETO DE APLICACION WEB A NIVEL DE JEE