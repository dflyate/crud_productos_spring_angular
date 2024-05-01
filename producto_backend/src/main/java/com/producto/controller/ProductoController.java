package com.producto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producto.entity.Producto;
import com.producto.service.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> show() {
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Integer id){
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al consultar en bd");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto == null) {
			response.put("mensaje", "el producto con id "+id+" no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> insertar(@RequestBody Producto producto){
		Producto productoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			productoNuevo = productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al insertar en bd");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "producto creado");
		response.put("producto", productoNuevo);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> editar(@RequestBody Producto producto,@PathVariable Integer id){
		Producto productoActualizado = null;
		Producto productoObtenido = productoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if(productoObtenido == null) {
			response.put("mensaje", "el producto con id "+id+" no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			productoObtenido.setNombre(producto.getNombre());
			productoObtenido.setDescripcion(producto.getDescripcion());
			productoObtenido.setExistencias(producto.getExistencias());
			productoObtenido.setFechaRegistro(producto.getFechaRegistro());
			productoObtenido.setFoto(producto.getFoto());
			productoObtenido.setPrecio(producto.getPrecio());
			productoActualizado = productoService.save(productoObtenido);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al actualizar en bd");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "producto actualizado");
		response.put("producto", productoActualizado);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.deleteById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "error al eliminar en bd");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "producto eliminado");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
}
