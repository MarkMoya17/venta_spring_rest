package com.venta.servicios.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venta.proy.Categoria;
import com.venta.proy.Cliente;
import com.venta.proy.Detalle;
import com.venta.proy.Documento;
import com.venta.proy.Factura;
import com.venta.proy.Producto;
import com.venta.repositorios.CategoriaRepository;
import com.venta.repositorios.ClienteRepository;
import com.venta.repositorios.DetalleRepository;
import com.venta.repositorios.DocumentoRepository;
import com.venta.repositorios.FacturaRepository;
import com.venta.repositorios.ProductoRepository;
import com.venta.servicios.ServicioVenta;

@Service
public class ServicioVentaJPA implements ServicioVenta {
	@Autowired
	private ProductoRepository repoproducto;
	@Autowired
	private CategoriaRepository repocategoria;
	@Autowired
	private ClienteRepository repocliente;
	@Autowired
	private DocumentoRepository repodocumento;
	@Autowired
	private FacturaRepository repofactura;
	@Autowired
	private DetalleRepository repodetalle;
	
	public ProductoRepository getRepoproducto() {
		return repoproducto;
	}
	
	public void setRepoproducto(ProductoRepository repoproducto) {
		this.repoproducto = repoproducto;
	}

	public CategoriaRepository getRepocategoria() {
		return repocategoria;
	}

	
	public void setRepocategoria(CategoriaRepository repocategoria) {
		this.repocategoria = repocategoria;
	}
	
	public ClienteRepository getRepocliente() {
		return repocliente;
	}

	
	public void setRepocliente(ClienteRepository repocliente) {
		this.repocliente = repocliente;
	}
	
	public DocumentoRepository getRepodocumento() {
		return repodocumento;
	}

	public void setRepodocumento(DocumentoRepository repodocumento) {
		this.repodocumento = repodocumento;
	}

	public FacturaRepository getRepofactura() {
		return repofactura;
	}

	public void setRepofactura(FacturaRepository repofactura) {
		this.repofactura = repofactura;
	}
	
	public DetalleRepository getRepoDetalle() {
		return repodetalle;
	}

	public void setRepodetalle(DetalleRepository repodetalle) {
		this.repodetalle = repodetalle;
	}

	public Producto findOneProd(Integer id) {
		return repoproducto.findOne(id);
	}

	
	public Iterable<Producto> findAllProd() {
		return repoproducto.findAll();
	}

	 @Transactional
	public void saveProd(Producto producto) {
		repoproducto.save(producto);
	}
	
	 @Transactional
	public void updateProd(Producto producto){
		 repoproducto.save(producto);
	}
	

	 @Transactional
	public void deleteProd(Producto producto) {
		repoproducto.delete(producto);
	}

	
	public Categoria findOneCat(Integer id) {
		return repocategoria.findOne(id);
	}

	
	public Iterable<Categoria> findAllCat() {
		return repocategoria.findAll();
	}

	 @Transactional
	public void saveCat(Categoria categoria) {
		repocategoria.save(categoria);
	}
	 @Transactional
	public void deleteCat(Categoria categoria) {
		repocategoria.delete(categoria);
	}
	 
	public Cliente findOneCli(Integer id) {
		return repocliente.findOne(id);
	}
	
	public Iterable<Cliente> findAllCli() {
		return repocliente.findAll();
	}

	 @Transactional
	public void saveCli(Cliente cliente) {
		repocliente.save(cliente);
	}
	 @Transactional
	public void deleteCli(Cliente cliente) {
		repocliente.delete(cliente);
	}

	 @Transactional
	public void updateCat(Categoria categoria) {
		 repocategoria.save(categoria);
	}
	 @Transactional
	public void updateCli(Cliente cliente) {
		repocliente.save(cliente);
		
	}

	public Documento findOneDoc(Integer id) {
		// TODO Auto-generated method stub
		return repodocumento.findOne(id);
	}

	public Iterable<Documento> findAllDoc() {
		// TODO Auto-generated method stub
		return repodocumento.findAll();
	}

	@Transactional
	public void saveDoc(Documento documento) {
		// TODO Auto-generated method stub
		repodocumento.save(documento);
	}

	@Transactional
	public void updateDoc(Documento documento) {
		// TODO Auto-generated method stub
		repodocumento.save(documento);
	}

	@Transactional
	public void deleteDoc(Documento documento) {
		// TODO Auto-generated method stub
		repodocumento.delete(documento);
	}

	//@Override
	public Iterable<Producto> findByNombre(String nombre) {
		return repoproducto.findByNombre(nombre);
	}

	public Iterable<Producto> findByEstado(int estado) {
		return repoproducto.findByEstado(estado);
	}



	public Factura findOneFact(Integer id) {
		return repofactura.findOne(id);		
	}

	public Iterable<Factura> findAllFact() {
		return repofactura.findAll();
	}
	@Transactional
	public void saveFact(Factura factura) {
		repofactura.save(factura);
		
	}
	@Transactional
	public void updateFactd(Factura factura) {
		repofactura.save(factura);
		
	}
	@Transactional
	public void deleteFact(Factura factura) {
		repofactura.delete(factura);
		
	}
	

	public Detalle findOneDet(Integer id) {
		return repodetalle.findOne(id);
	}

	public Iterable<Detalle> findAllDet() {
		return repodetalle.findAll();
	}
	
	@Transactional
	public void saveDet(Detalle detalle) {
		repodetalle.save(detalle);
	}
	
	@Transactional
	public void updateDet(Detalle detalle) {
		repodetalle.save(detalle);
	}

	@Transactional
	public void deleteDet(Detalle detalle) {
		repodetalle.delete(detalle);
		
	}








}
