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

import com.fitness.modelo.Rol;
import com.fitness.servicios.RolService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RolController {
	@Autowired
	private RolService rolService;

	@GetMapping("/roles")
	public List<Rol> listaRoles() {
		return rolService.listaRoles();
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<?> encontrarRol(@PathVariable Integer idRol) {
		Rol rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rol = rolService.encontrarRol(idRol);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (rol == null) {
			response.put("mensaje", "El Rol ID: ".concat(idRol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}

	// Create
	@PostMapping("/roles")
	public ResponseEntity<?> guardar(@RequestBody Rol rol) {
		Rol rolNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rolNuevo = rolService.guardar(rol);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Rol ha sido creado con exito!");
		response.put("rol", rolNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idRol) {
		Map<String, Object> response = new HashMap<>();
		try {
			rolService.eliminar(idRol);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Rol en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Rol ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Rol rol, @PathVariable Integer idRol) {
		Rol rolActual = rolService.encontrarRol(idRol);
		Rol rolUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (rolActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el Rol ID: "
					.concat(idRol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			rolActual.setDescripcion(rol.getDescripcion());
			rolUpdated = rolService.guardar(rolActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Rol en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Rol ha sido actualizado con exito!");
		response.put("rol", rolUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
