package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.modelo.Localidad;
import com.fitness.repositorios.LocalidadRepository;
@Service
public class LocalidadServiceImpl implements LocalidadService {
	@Autowired
    private LocalidadRepository localidadRepository;
	@Override
	public List<Localidad> listaLocalidades() {
	    return (List<Localidad>) localidadRepository.findAll();
	}

	@Override
	public void guardar(Localidad localidad) {
		localidadRepository.save(localidad);
	}

	@Override
	public void eliminar(Localidad localidad) {
		localidadRepository.delete(localidad);	

	}

	@Override
	public Localidad encontrarLocalidad(Localidad localidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
