package com.empresaproductos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaproductos.models.entity.Producto;
import com.empresaproductos.models.services.IProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;

	@GetMapping
	public List<Producto> listar() {
		return productoService.findAll();
	}
	
	@GetMapping("/page/{page}")
	public Page<Producto> listar(@PathVariable Integer page) {
		return productoService.findAll(PageRequest.of(page, 5));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Producto producto = null;
		Map<String, Object> resultado = new HashMap<>();
		try {
			producto = productoService.findBy(id);
		}catch(DataAccessException e) {
			resultado.put("mensaje", "Error al consultar en base de datos");
			resultado.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto == null) {
			resultado.put("mensaje", "El producto con id "+id+" no existe");
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result){
		Producto productoNew = null;
		Map<String, Object> resultado = new HashMap<>();
		if(result.hasErrors()) {
			List<String>errors = result.getFieldErrors().stream().map(
					err -> "El campo "+err.getField()+" "+err.getDefaultMessage()
					).collect(Collectors.toList());
			resultado.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.BAD_REQUEST);
		}
		try {
			productoNew = productoService.save(producto);
		}catch(DataAccessException e) {
			resultado.put("mensaje", "Error al insertar en base de datos");
			resultado.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resultado.put("mensaje", "Producto creado con éxito");
		resultado.put("producto", productoNew);
		return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,@PathVariable Integer id){
		Producto productoActual = productoService.findBy(id);
		Producto productoUpdated = null;
		Map<String, Object> resultado = new HashMap<>();
		if(result.hasErrors()) {
			List<String>errors = result.getFieldErrors().stream().map(
					err -> "El campo "+err.getField()+" "+err.getDefaultMessage()
					).collect(Collectors.toList());
			resultado.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.BAD_REQUEST);
		}
		if(productoActual == null) {
			resultado.put("mensaje", "El producto con id "+id+" no existe");
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.NOT_FOUND); 
		}
		try {
			productoActual.setNombre(producto.getNombre());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setExistencias(producto.getExistencias());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setFechaRegistro(producto.getFechaRegistro());
			productoUpdated = productoService.save(producto);
		}catch(DataAccessException e) {
			resultado.put("mensaje", "Error al actualizar en base de datos");
			resultado.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resultado.put("mensaje", "Producto actualizado con éxito");
		resultado.put("producto", productoUpdated);
		return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Map<String, Object> resultado = new HashMap<>();
		try {
			productoService.delete(id);
		}catch(DataAccessException e) {
			resultado.put("mensaje", "Error al eliminar en base de datos");
			resultado.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resultado.put("mensaje", "Producto eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.OK);
	}
}
