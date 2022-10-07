package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.modelo.Provincia;
import com.fitness.repositorios.ProvinciaRepository;
@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	@Autowired
    private ProvinciaRepository provinciaRepository;
	@Override
	public List<Provincia> listaProvincias() {
	    return (List<Provincia>) provinciaRepository.findAll();
	}

	@Override
	public void guardar(Provincia provincia) {
		provinciaRepository.save(provincia);
	}

	@Override
	public void eliminar(Provincia provincia) {
		provinciaRepository.delete(provincia);	
	}

	@Override
	public Provincia encontrarProvincia(Provincia provincia) {
		// TODO Auto-generated method stub
		return null;
	}

}
