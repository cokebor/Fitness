package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Rubro;
import com.fitness.repositorios.RubroRepository;

@Service
public class RubroServiceImpl implements RubroService {

	@Autowired
    private RubroRepository rubroRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Rubro> listaRubros() {
	    return (List<Rubro>) rubroRepository.findAll();
	}

	@Override
	@Transactional
	public Rubro guardar(Rubro rubro) {
		return rubroRepository.save(rubro);
	}

	@Override
	@Transactional
	public void eliminar(Integer idubro) {
		rubroRepository.deleteById(idubro);	
	}

	@Override
	@Transactional(readOnly = true)
	public Rubro encontrarRubro(Integer idRubro) {
		// TODO Auto-generated method stub
		return rubroRepository.findById(idRubro).orElse(null);
	}
}
