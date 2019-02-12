package com.venta.repositorios;
public interface GenericRepository <T, K>{
	public T findOne(K k);
	public Iterable<T> findAll();
	public void save(T tipo);
	public void update(T tipo);
	public void delete(T tipo);
}
