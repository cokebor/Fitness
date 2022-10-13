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

import com.fitness.modelo.Localidad;
import com.fitness.servicios.LocalidadService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocalidadController {
	@Autowired
	private LocalidadService localidadService;

	@GetMapping("/localidades")
	public List<Localidad> listaLocalidades() {
		return localidadService.listarLocalidades();
	}

	@GetMapping("/localidades/{id}")
	public ResponseEntity<?> encontrarLocalidad(@PathVariable Integer idLocalidad) {
		Localidad localidad = null;
		Map<String, Object> response = new HashMap<>();

		try {
			localidad = localidadService.encontrarLocalidad(idLocalidad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (localidad == null) {
			response.put("mensaje",
					"La Localidad ID: ".concat(idLocalidad.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Localidad>(localidad, HttpStatus.OK);
	}

	// Create
	@PostMapping("/localidades")
	public ResponseEntity<?> guardar(@RequestBody Localidad localidad) {
		Localidad localidadNueva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			localidadNueva = localidadService.guardar(localidad);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Localidad ha sido creada con exito!");
		response.put("localidad", localidadNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/localidades/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idLocalidad) {
		Map<String, Object> response = new HashMap<>();
		try {
			localidadService.eliminar(idLocalidad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Localidad en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Localidad ha sido eliminada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/localidades/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Localidad localidad, @PathVariable Integer idLocalidad) {
		Localidad localidadActual = localidadService.encontrarLocalidad(idLocalidad);
		Localidad localidadUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (localidadActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la Localidad ID: "
					.concat(idLocalidad.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			localidadActual.setDescripcion(localidad.getDescripcion());
			localidadActual.setProvincia(localidad.getProvincia());
			localidadUpdated = localidadService.guardar(localidadActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Localidad en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Localidad ha sido actualizada con exito!");
		response.put("localidad", localidadUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
