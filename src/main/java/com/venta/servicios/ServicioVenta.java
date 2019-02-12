package com.venta.servicios;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.proy.Detalle;
import com.venta.proy.Documento;
import com.venta.proy.Factura;
import com.venta.proy.Producto;
import com.venta.repositorios.CategoriaRepository;
import com.venta.repositorios.ClienteRepository;
import com.venta.repositorios.DetalleRepository;
import com.venta.repositorios.FacturaRepository;
import com.venta.repositorios.ProductoRepository;

public interface ServicioVenta {

	DetalleRepository getRepoDetalle();
	
	FacturaRepository getRepofactura();
	
	ProductoRepository getRepoproducto();

	void setRepoproducto(ProductoRepository repoproducto);

	CategoriaRepository getRepocategoria();

	void setRepocategoria(CategoriaRepository repocategoria);
	
	ClienteRepository getRepocliente();

	void setRepocliente(ClienteRepository repocliente);

	Producto findOneProd(Integer id);

	Iterable<Producto> findAllProd();

	void saveProd(Producto producto);
	
	void updateProd(Producto producto);

	void deleteProd(Producto producto);

	Categoria findOneCat(Integer id);

	Iterable<Categoria> findAllCat();

	void saveCat(Categoria categoria);
	
	void updateCat(Categoria categoria);

	void deleteCat(Categoria categoria);
	
	Cliente findOneCli(Integer id);

	Iterable<Cliente> findAllCli();

	void saveCli(Cliente cliente);
	
	void updateCli(Cliente cliente);

	void deleteCli(Cliente cliente);
	
	Documento findOneDoc(Integer id);

	Iterable<Documento> findAllDoc();

	void saveDoc(Documento documento);
	
	void updateDoc(Documento documento);

	void deleteDoc(Documento documento);
	
	public Iterable<Producto> findByNombre(String nombre);
	public Iterable<Producto> findByEstado(int estado);
	
	
	Factura findOneFact(Integer id);

	Iterable<Factura> findAllFact();

	void saveFact(Factura factura);
	
	void updateFactd(Factura factura);

	void deleteFact(Factura factura);
	
	
	
	Detalle findOneDet(Integer id);

	Iterable<Detalle> findAllDet();

	void saveDet(Detalle detalle);
	
	void updateDet(Detalle detalle);

	void deleteDet(Detalle detalle);
	
	

}