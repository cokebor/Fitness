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

import com.fitness.modelo.Disciplina;
import com.fitness.servicios.DisciplinaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DisciplinaController {
	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping("/disciplinas")
	public List<Disciplina> listaDisciplinas() {
		return disciplinaService.listaDisciplinas();
	}

	@GetMapping("/disciplinas/{id}")
	public ResponseEntity<?> encontrarDisciplina(@PathVariable Integer idDisciplina) {
		Disciplina disciplina = null;
		Map<String, Object> response = new HashMap<>();

		try {
			disciplina = disciplinaService.encontrarDisciplina(idDisciplina);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (disciplina == null) {
			response.put("mensaje",
					"La Disciplina ID: ".concat(idDisciplina.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
	}

	// Create
	@PostMapping("/disciplinas")
	public ResponseEntity<?> guardar(@RequestBody Disciplina disciplina) {
		Disciplina disciplinaNueva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			disciplinaNueva = disciplinaService.guardar(disciplina);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Disciplina ha sido creada con exito!");
		response.put("disciplina", disciplinaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/disciplinas/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idDisciplina) {
		Map<String, Object> response = new HashMap<>();
		try {
			disciplinaService.eliminar(idDisciplina);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Disciplina en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Disciplina ha sido eliminada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/disciplinas/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Disciplina disciplina, @PathVariable Integer idDisciplina) {
		Disciplina disciplinaActual = disciplinaService.encontrarDisciplina(idDisciplina);
		Disciplina disciplinaUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (disciplinaActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la Disciplina ID: "
					.concat(idDisciplina.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			disciplinaActual.setDescripcion(disciplina.getDescripcion());
			disciplinaUpdated = disciplinaService.guardar(disciplinaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Disciplina en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Disciplina ha sido actualizada con exito!");
		response.put("disciplina", disciplinaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
