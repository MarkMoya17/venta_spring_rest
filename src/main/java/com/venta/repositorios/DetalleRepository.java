package com.venta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.proy.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer>{
	
}
