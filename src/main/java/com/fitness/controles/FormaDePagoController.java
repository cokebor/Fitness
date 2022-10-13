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

import com.fitness.modelo.FormaDePago;
import com.fitness.servicios.FormaDePagoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FormaDePagoController {
	@Autowired
	private FormaDePagoService formadePagoService;

	@GetMapping("/formasdepago")
	public List<FormaDePago> listaFormasDePago() {
		return formadePagoService.listaFormasDePago();
	}

	@GetMapping("/formasdepago/{id}")
	public ResponseEntity<?> encontrarFormaDePago(@PathVariable Integer idFormaDePago) {
		FormaDePago formaDePago = null;
		Map<String, Object> response = new HashMap<>();

		try {
			formaDePago = formadePagoService.encontrarFormaDePago(idFormaDePago);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (formaDePago == null) {
			response.put("mensaje",
					"La Forma De Pago ID: ".concat(idFormaDePago.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FormaDePago>(formaDePago, HttpStatus.OK);
	}

	// Create
	@PostMapping("/formasdepago")
	public ResponseEntity<?> guardar(@RequestBody FormaDePago formaDePago) {
		FormaDePago formaDePagoNueva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			formaDePagoNueva = formadePagoService.guardar(formaDePago);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Forma de Pago ha sido creada con exito!");
		response.put("formaDePago", formaDePagoNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/formasdepago/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idFormaDePago) {
		Map<String, Object> response = new HashMap<>();
		try {
			formadePagoService.eliminar(idFormaDePago);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Forma de Pago en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Forma de Pago ha sido eliminada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/formasdepago/{id}")
	public ResponseEntity<?> actualizar(@RequestBody FormaDePago formaDePago, @PathVariable Integer idFormaDePago) {
		FormaDePago formaDePagoActual = formadePagoService.encontrarFormaDePago(idFormaDePago);
		FormaDePago formaDePagoUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (formaDePagoActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la Disciplina ID: "
					.concat(idFormaDePago.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			formaDePagoActual.setDescripcion(formaDePago.getDescripcion());
			formaDePagoUpdated = formadePagoService.guardar(formaDePagoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Forma de Pago en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Forma de Pago ha sido actualizada con exito!");
		response.put("formaDePago",formaDePagoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
