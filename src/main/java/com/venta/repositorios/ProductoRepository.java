package com.venta.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.proy.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	public Iterable<Producto> findByNombre(String nombre);
	public Iterable<Producto> findByEstado(int estado);
	
}
