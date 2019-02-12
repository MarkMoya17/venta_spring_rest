package com.venta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.venta.web.conversores.CategoriaFormatter;

import org.springframework.format.FormatterRegistry;

@Configuration
@ComponentScan("com.venta.*")
@EnableWebMvc
@Import({ConfiguracionPersistencia.class,ConfiguracionMVC.class})
public class ConfiguracionSpring extends WebMvcConfigurerAdapter{
	
	//INYECTAMOS EL CONVERSOR DE CATEGORIAS
	@Autowired 
	private CategoriaFormatter catFormatter;
	
	//REGISTRAMOS UN MANEJADOR DE RECURSOS PARA MAPEAR LOS RECURSOS ESTATICOS..CSS, JS, ETC
	@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**")
	                .addResourceLocations("/resources/");
	    }  
	 
	 
	 //REGISTRO DE CONVERSORES 
	 @Override
	    public void addFormatters(FormatterRegistry registry) {
	        super.addFormatters(registry);
	        registry.addFormatter(catFormatter);
	       // registry.addFormatter(dateFormatter());
	    }

}