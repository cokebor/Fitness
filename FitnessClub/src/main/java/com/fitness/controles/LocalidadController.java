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

import com.fitness.modelo.Localidad;
import com.fitness.servicios.LocalidadService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocalidadController {
	@Autowired
	private LocalidadService localidadService;
    @GetMapping("/localidades")
    public List<Localidad> listaLocalidades()
    {
        return localidadService.listaLocalidades();
    }
    
    @PostMapping("/localidades")
    public void guardar(@RequestBody Localidad localidad)
    {
    	localidadService.guardar(localidad);
    }
    
    @DeleteMapping("/localidades/{id}")
    public String eliminar(@PathVariable("IdLocalidad") Localidad localidad)
    {
    	try {
    		localidadService.eliminar(localidad);
        return "Eliminacion Satisfactoria";
    	}catch(Exception e){
    		return "ERROR: No se pudo eliminar";
    	}
    }
}
