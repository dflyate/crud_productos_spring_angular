package com.producto.service;

import java.util.List;

import com.producto.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Producto findById(Integer id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Integer idproducto);

}
