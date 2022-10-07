package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.modelo.Pais;
import com.fitness.repositorios.PaisRepository;

@Service
public class PaisServiceImpl implements PaisService {
	@Autowired
    private PaisRepository paisRepository;
	
	@Override
	public List<Pais> listaPaises() {
	    return (List<Pais>) paisRepository.findAll();
	}

	@Override
	public void guardar(Pais pais) {
		paisRepository.save(pais);
	}

	@Override
	public void eliminar(Pais pais) {
		paisRepository.delete(pais);	
	}

	@Override
	public Pais encontrarPais(Pais pais) {
		// TODO Auto-generated method stub
		return null;
	}

}
