package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Pais;
import com.fitness.repositorios.PaisRepository;

@Service
public class PaisServiceImpl implements PaisService {
	@Autowired
    private PaisRepository paisRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Pais> listaPaises() {
		return (List<Pais>)paisRepository.findAll();
	}

	@Override
	@Transactional
	public Pais guardar(Pais pais) {
		// TODO Auto-generated method stub
		return paisRepository.save(pais);
	}

	@Override
	@Transactional
	public void eliminar(Integer idPais) {
		paisRepository.deleteById(idPais);
	}

	@Override
	@Transactional(readOnly = true)
	public Pais encontrarPais(Integer idPais) {
		// TODO Auto-generated method stub
		return paisRepository.findById(idPais).orElse(null);
	}
	
	
}
