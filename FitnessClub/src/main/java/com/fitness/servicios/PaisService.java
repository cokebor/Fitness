package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Pais;

public interface PaisService {
	public List<Pais> listaPaises();
	
	public void guardar(Pais pais);
	
	public void eliminar(Pais pais);
	public Pais encontrarPais(Pais pais);
}
