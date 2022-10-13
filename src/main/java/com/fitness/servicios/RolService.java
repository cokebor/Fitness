package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Rol;


public interface RolService {
	public List<Rol> listaRoles();
	
	public Rol guardar(Rol rol);
	
	public void eliminar(Integer idRol);
	public Rol encontrarRol(Integer idRol);
}


