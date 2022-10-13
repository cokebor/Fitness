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

import com.fitness.modelo.Provincia;
import com.fitness.servicios.ProvinciaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProvinciaController {
	@Autowired
	private ProvinciaService provinciaService;

	@GetMapping("/provincias")
	public List<Provincia> listarProvincias() {
		return provinciaService.listaProvincias();
	}

	@GetMapping("/provincias/{id}")
	public ResponseEntity<?> encontrarProvincia(@PathVariable Integer idProvincia) {
		Provincia provincia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			provincia = provinciaService.encontrarProvincia(idProvincia);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (provincia == null) {
			response.put("mensaje",
					"La Provincia ID: ".concat(idProvincia.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Provincia>(provincia, HttpStatus.OK);
	}

	// Create
	@PostMapping("/provincias")
	public ResponseEntity<?> guardar(@RequestBody Provincia provincia) {
		Provincia provinciaNueva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			provinciaNueva = provinciaService.guardar(provincia);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Provincia ha sido creada con exito!");
		response.put("provincia", provinciaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/provincias/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idProvincia) {
		Map<String, Object> response = new HashMap<>();
		try {
			provinciaService.eliminar(idProvincia);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Provincia en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Provincia ha sido eliminada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/provincias/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Provincia provincia, @PathVariable Integer idProvincia) {
		Provincia provinciaActual = provinciaService.encontrarProvincia(idProvincia);
		Provincia provinciaUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (provinciaActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la Povincia ID: "
					.concat(idProvincia.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			provinciaActual.setDescripcion(provinciaActual.getDescripcion());
			provinciaActual.setPais(provinciaActual.getPais());
			provinciaUpdated = provinciaService.guardar(provinciaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Provincia en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Provincia ha sido actualizada con exito!");
		response.put("provincia", provinciaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
