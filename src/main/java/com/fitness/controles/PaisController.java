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

import com.fitness.modelo.Pais;
import com.fitness.servicios.PaisService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PaisController {
	@Autowired
	private PaisService paisService;

	@GetMapping("/paises")
	public List<Pais> listaPaises() {
		return paisService.listaPaises();
	}

	@GetMapping("/paises/{id}")
	public ResponseEntity<?> encontrarPais(@PathVariable Integer idPais) {
		Pais pais = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pais = paisService.encontrarPais(idPais);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pais == null) {
			response.put("mensaje", "El Pais ID: ".concat(idPais.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pais>(pais, HttpStatus.OK);
	}

	// Create
	@PostMapping("/paises")
	public ResponseEntity<?> guardar(@RequestBody Pais pais) {
		Pais paisNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			paisNuevo = paisService.guardar(pais);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Pais ha sido creado con exito!");
		response.put("pais", paisNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/paises/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idPais) {
		Map<String, Object> response = new HashMap<>();
		try {
			paisService.eliminar(idPais);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Pais en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Pais ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Pais pais, @PathVariable Integer idPais) {
		Pais paisActual = paisService.encontrarPais(idPais);
		Pais paisUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (paisActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el Pais ID: "
					.concat(idPais.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			paisActual.setDescripcion(pais.getDescripcion());
			paisUpdated = paisService.guardar(paisActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Pais en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Pais ha sido actualizado con exito!");
		response.put("pais", paisUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
