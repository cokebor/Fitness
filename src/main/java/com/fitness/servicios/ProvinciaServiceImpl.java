package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Provincia;
import com.fitness.repositorios.ProvinciaRepository;
@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	@Autowired
    private ProvinciaRepository provinciaRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Provincia> listaProvincias() {
	    return (List<Provincia>) provinciaRepository.findAll();
	}

	@Override
	@Transactional
	public Provincia guardar(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	@Override
	@Transactional
	public void eliminar(Integer idProvincia) {
		provinciaRepository.deleteById(idProvincia);	
	}

	@Override
	@Transactional(readOnly = true)
	public Provincia encontrarProvincia(Integer idProvincia) {
		// TODO Auto-generated method stub
		return provinciaRepository.findById(idProvincia).orElse(null);
	}

}
