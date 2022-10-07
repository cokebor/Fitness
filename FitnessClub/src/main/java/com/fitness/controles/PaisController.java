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

import com.fitness.modelo.Pais;
import com.fitness.servicios.PaisService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PaisController {
	@Autowired
	private PaisService paisService;
    @GetMapping("/paises")
    public List<Pais> listaPaises()
    {
        return paisService.listaPaises();
    }
    
    // Create
    @PostMapping("/paises")
    public void guardar(@RequestBody Pais pais)
    {
        paisService.guardar(pais);
    }

 
    // Delete
    @DeleteMapping("/paises/{id}")
    public String eliminar(@PathVariable("IdPais") Pais pais)
    {
    	try {
    		paisService.eliminar(pais);
        return "Eliminacion Satisfactoria";
    	}catch(Exception e){
    		return "ERROR: No se pudo eliminar";
    	}
    }
}
