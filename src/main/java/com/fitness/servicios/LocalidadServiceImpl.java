package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Localidad;
import com.fitness.repositorios.LocalidadRepository;
@Service
public class LocalidadServiceImpl implements LocalidadService {
	@Autowired
    private LocalidadRepository localidadRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Localidad> listarLocalidades() {
		return (List<Localidad>)localidadRepository.findAll();
	}

	@Override
	@Transactional
	public Localidad guardar(Localidad localidad) {
		// TODO Auto-generated method stub
		return localidadRepository.save(localidad);
	}

	@Override
	@Transactional
	public void eliminar(Integer idPais) {
		localidadRepository.deleteById(idPais);
	}

	@Override
	@Transactional(readOnly = true)
	public Localidad encontrarLocalidad(Integer idLocalidad) {
		// TODO Auto-generated method stub
		return localidadRepository.findById(idLocalidad).orElse(null);
	}
}
