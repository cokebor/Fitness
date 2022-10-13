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

import com.fitness.modelo.Usuario;
import com.fitness.servicios.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios() {
		return usuarioService.listarUsuarios();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> encontrarUsuario(@PathVariable Integer idUsuario) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.encontrarUsuario(idUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (usuario == null) {
			response.put("mensaje",
					"El Usuario ID: ".concat(idUsuario.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	// Create
	@PostMapping("/usuarios")
	public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioNuevo = usuarioService.guardar(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Usuario ha sido creado con exito!");
		response.put("usuario", usuarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idUsuario) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.eliminar(idUsuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Usuario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Usuario ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Usuario usuario, @PathVariable Integer idUsuario) {
		Usuario usuarioActual = usuarioService.encontrarUsuario(idUsuario);
		Usuario usuarioUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el Usuario ID: "
					.concat(idUsuario.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setMail(usuario.getMail());
			usuarioActual.setDireccion(usuario.getDireccion());
			usuarioActual.setLocalidad(usuario.getLocalidad());
			usuarioActual.setDNI(usuario.getDNI());
			usuarioActual.setPassword(usuario.getPassword());
			usuarioActual.setRol(usuario.getRol());
			usuarioActual.setEstado(usuario.getEstado());
			usuarioUpdated = usuarioService.guardar(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Usuario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Usuario ha sido actualizado con exito!");
		response.put("usuario", usuarioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
