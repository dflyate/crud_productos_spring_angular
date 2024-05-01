package com.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.producto.dao.IProductoDao;
import com.producto.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao productoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Producto findById(Integer id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoDao.save(producto);
	}

	@Transactional()
	@Override
	public void deleteById(Integer idproducto) {
		// TODO Auto-generated method stub
		productoDao.deleteById(idproducto);
	}

}
