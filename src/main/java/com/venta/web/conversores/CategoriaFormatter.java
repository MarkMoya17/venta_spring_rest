package com.venta.web.conversores;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import com.venta.proy.Categoria;
import com.venta.servicios.ServicioVenta;



@Service
public class CategoriaFormatter implements Formatter<Categoria> {

    @Autowired
    private ServicioVenta servicioVenta;


    public CategoriaFormatter() {
        super();
    }

    public Categoria parse(String text,  Locale locale) throws ParseException {
           Integer Id = Integer.valueOf(text);
        return this.servicioVenta.findOneCat(Id);
    }


    public String print(Categoria cat, Locale locale) {
         return (cat != null ? Integer.toString(cat.getId()) : "");
    }

}