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

import com.fitness.modelo.Provincia;
import com.fitness.servicios.ProvinciaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProvinciaController {
	@Autowired
	private ProvinciaService provinciaService;
    @GetMapping("/povincias")
    public List<Provincia> listaProvincias()
    {
        return provinciaService.listaProvincias();
    }
    
    // Create
    @PostMapping("/povincias")
    public void guardar(@RequestBody Provincia provincia)
    {
    	provinciaService.guardar(provincia);
    }

 
    // Delete
    @DeleteMapping("/povincias/{id}")
    public String eliminar(@PathVariable("IdProvincia") Provincia provincia)
    {
    	try {
    		provinciaService.eliminar(provincia);
        return "Eliminacion Satisfactoria";
    	}catch(Exception e){
    		return "ERROR: No se pudo eliminar";
    	}
    }
}
