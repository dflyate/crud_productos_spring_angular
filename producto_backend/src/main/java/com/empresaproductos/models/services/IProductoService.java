package com.empresaproductos.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empresaproductos.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Page<Producto> findAll(Pageable pageable);
	public Producto findBy(Integer id);
	public Producto save(Producto producto);
	public void delete(Integer id);
}
