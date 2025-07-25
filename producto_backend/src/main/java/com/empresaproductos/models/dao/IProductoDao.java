package com.empresaproductos.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaproductos.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Integer>{

}
