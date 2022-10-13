package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Localidad;


public interface LocalidadService {
	public List<Localidad> listarLocalidades();
	
	public Localidad guardar(Localidad localidad);
	
	public void eliminar(Integer idLocalidad);
	
	public Localidad encontrarLocalidad(Integer idLocalidad);
}


