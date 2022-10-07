package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Rol;


public interface RolService {
	public List<Rol> listaRoles();
	
	public void guardar(Rol rol);
	
	public void eliminar(Rol rol);
	public Rol encontrarRol(Rol rol);
}
