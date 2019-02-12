package com.venta.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.venta.proy.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
