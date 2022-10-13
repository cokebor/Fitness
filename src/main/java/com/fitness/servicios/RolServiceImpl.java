package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Rol;
import com.fitness.repositorios.RolRepository;
@Service
public class RolServiceImpl implements RolService {
	@Autowired
    private RolRepository rolRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Rol> listaRoles() {
	    return (List<Rol>) rolRepository.findAll();
	}

	@Override
	@Transactional
	public Rol guardar(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	@Transactional
	public void eliminar(Integer idRol) {
		rolRepository.deleteById(idRol);	
	}

	@Override
	@Transactional(readOnly = true)
	public Rol encontrarRol(Integer idRol) {
		// TODO Auto-generated method stub
		return rolRepository.findById(idRol).orElse(null);
	}

}
