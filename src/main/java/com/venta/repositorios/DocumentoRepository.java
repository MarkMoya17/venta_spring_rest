package com.venta.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.proy.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer>{
	

}
