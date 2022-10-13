package com.fitness.controles;

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

import com.fitness.modelo.Producto;
import com.fitness.servicios.ProductoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> listaProductos() {
		return productoService.listaProductos();
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<?> encontrarProducto(@PathVariable Integer idProducto) {
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.encontrarProducto(idProducto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (producto == null) {
			response.put("mensaje", "El Producto ID: ".concat(idProducto.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	// Create
	@PostMapping("/productos")
	public ResponseEntity<?> guardar(@RequestBody Producto producto) {
		Producto productoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			productoNuevo = productoService.guardar(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Producto ha sido creado con exito!");
		response.put("pais", productoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idProducto) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.eliminar(idProducto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Producto en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Producto ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/productos/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Producto producto, @PathVariable Integer idProducto) {
		Producto productoActual = productoService.encontrarProducto(idProducto);
		Producto productoUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (productoActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el Producto ID: "
					.concat(idProducto.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setRubro(producto.getRubro());
			productoActual.setDisciplina(producto.getDisciplina());
			productoActual.setPrecioUnitario(producto.getPrecioUnitario());
			productoActual.setImagen(producto.getImagen());
			productoActual.setStock(producto.getStock());
			productoActual.setEsServicio(producto.getEsServicio());
			productoActual.setEstado(producto.getEstado());
			productoUpdated = productoService.guardar(productoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Producto en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Producto ha sido actualizado con exito!");
		response.put("producto", productoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
