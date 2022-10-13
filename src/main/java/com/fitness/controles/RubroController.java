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

import com.fitness.modelo.Rubro;
import com.fitness.servicios.RubroService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RubroController {
	@Autowired
	private RubroService rubroService;

	@GetMapping("/rubros")
	public List<Rubro> listaRubros() {
		return rubroService.listaRubros();
	}

	@GetMapping("/rubros/{id}")
	public ResponseEntity<?> encontrarRubro(@PathVariable Integer idRubro) {
		Rubro rubro = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rubro = rubroService.encontrarRubro(idRubro);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (rubro == null) {
			response.put("mensaje",
					"El Rubro ID: ".concat(idRubro.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Rubro>(rubro, HttpStatus.OK);
	}

	// Create
	@PostMapping("/rubros")
	public ResponseEntity<?> guardar(@RequestBody Rubro rubro) {
		Rubro rubroNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rubroNuevo = rubroService.guardar(rubro);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Rubro ha sido creado con exito!");
		response.put("rubro", rubroNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/rubros/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idRubros) {
		Map<String, Object> response = new HashMap<>();
		try {
			rubroService.eliminar(idRubros);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Rubro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Rubro ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/rubros/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Rubro rubro, @PathVariable Integer idRubro) {
		Rubro rubroActual = rubroService.encontrarRubro(idRubro);
		Rubro rubroUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (rubroActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el Rubro ID: "
					.concat(idRubro.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			rubroActual.setDescripcion(rubro.getDescripcion());
			rubroUpdated = rubroService.guardar(rubroActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Rubro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Rubro ha sido actualizado con exito!");
		response.put("rubro", rubroUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
