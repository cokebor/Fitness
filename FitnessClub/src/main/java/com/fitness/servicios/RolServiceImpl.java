package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.modelo.Rol;
import com.fitness.repositorios.RolRepository;
@Service
public class RolServiceImpl implements RolService {
	@Autowired
    private RolRepository rolRepository;
	@Override
	public List<Rol> listaRoles() {
	    return (List<Rol>) rolRepository.findAll();
	}

	@Override
	public void guardar(Rol rol) {
		rolRepository.save(rol);
	}

	@Override
	public void eliminar(Rol rol) {
		rolRepository.delete(rol);	
	}

	@Override
	public Rol encontrarRol(Rol rol) {
		// TODO Auto-generated method stub
		return null;
	}

}
