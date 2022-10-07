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

import com.fitness.modelo.Rol;
import com.fitness.servicios.RolService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RolController {
	@Autowired
	private RolService rolService;
    @GetMapping("/roles")
    public List<Rol> listaRoles()
    {
        return rolService.listaRoles();
    }
    
    @PostMapping("/roles")
    public void guardar(@RequestBody Rol rol)
    {
    	rolService.guardar(rol);
    }
    
    @DeleteMapping("/roles/{id}")
    public String eliminar(@PathVariable("IdRol") Rol rol)
    {
    	try {
    		rolService.eliminar(rol);
        return "Eliminacion Satisfactoria";
    	}catch(Exception e){
    		return "ERROR: No se pudo eliminar";
    	}
    }
}
