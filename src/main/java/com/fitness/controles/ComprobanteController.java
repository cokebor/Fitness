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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.modelo.Comprobante;
import com.fitness.servicios.ComprobanteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ComprobanteController {
	@Autowired
	private ComprobanteService comprobanteService;

	@GetMapping("/comprobantes")
	public List<Comprobante> listaComprobantes() {
		return comprobanteService.listaComprobantes();
	}

	@GetMapping("/comprobantes/{id}")
	public ResponseEntity<?> encontrarComprobante(@PathVariable Integer idComprobante) {
		Comprobante comprobante = null;
		Map<String, Object> response = new HashMap<>();

		try {
			comprobante = comprobanteService.encontrarComprobante(idComprobante);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (comprobante == null) {
			response.put("mensaje",
					"El comprobante ID: ".concat(idComprobante.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Comprobante>(comprobante, HttpStatus.OK);
	}

	// Create
	@PostMapping("/comprobantes")
	public ResponseEntity<?> guardar(@RequestBody Comprobante comprobante) {
		Comprobante comprobanteNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			comprobanteNuevo = comprobanteService.guardar(comprobante);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Comprobante ha sido creado con exito!");
		response.put("comprobante", comprobanteNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/comprobantes/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idComprobante) {
		Map<String, Object> response = new HashMap<>();
		try {
			comprobanteService.eliminar(idComprobante);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Comprobante en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Comprobante ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
