package com.fitness.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Usuario> listaUsuarios()
    {
        return usuarioService.listaUsuarios();
    }
    
    @PostMapping("/usuarios")
    public void guardar(@RequestBody Usuario usuario)
    {
    	usuarioService.guardar(usuario);
    }
    
    @DeleteMapping("/usuarios/{id}")
    public String eliminar(@PathVariable("IdUsuario") Usuario usuario)
    {
    	try {
    		usuarioService.eliminar(usuario);
        return "Eliminacion Satisfactoria";
    	}catch(Exception e){
    		return "ERROR: No se pudo eliminar";
    	}
    }
}
