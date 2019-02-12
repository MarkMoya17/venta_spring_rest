package com.venta.proy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="factura")
public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int nrofactura;
	private Date fecha;
	private int estado;
	private double subtotal;
	private double igv;
	private double total;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="documento_id")
	private Documento documento;
//	@JsonIgnore
//	@OneToMany(mappedBy = "categoria", cascade=CascadeType.ALL,orphanRemoval=true)
//	private List<Producto> productos = new ArrayList<Producto>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="factura", cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Detalle> detalles = new ArrayList<Detalle>(); 

	public Factura(int id, int nrofactura, Date fecha, int estado, double subtotal, double igv, double total,
		Cliente cliente, Documento documento, List<Detalle> detalles) {
		super();
		this.id = id;
		this.nrofactura = nrofactura;
		this.fecha = fecha;
		this.estado = estado;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.cliente = cliente;
		this.documento = documento;
		this.detalles = detalles;
	}

	public Factura(int id) {
		super();
		this.id = id;
	}

	public Factura() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNrofactura() {
		return nrofactura;
	}

	public void setNrofactura(int nrofactura) {
		this.nrofactura = nrofactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}	

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estado;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(igv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nrofactura;
		temp = Double.doubleToLongBits(subtotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (estado != other.estado)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(igv) != Double.doubleToLongBits(other.igv))
			return false;
		if (nrofactura != other.nrofactura)
			return false;
		if (Double.doubleToLongBits(subtotal) != Double.doubleToLongBits(other.subtotal))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}

	
	

	
	
	
	
}
