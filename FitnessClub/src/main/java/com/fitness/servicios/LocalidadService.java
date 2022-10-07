package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Localidad;


public interface LocalidadService {
	public List<Localidad> listaLocalidades();
	
	public void guardar(Localidad localidad);
	
	public void eliminar(Localidad localidad);
	public Localidad encontrarLocalidad(Localidad localidad);
}
