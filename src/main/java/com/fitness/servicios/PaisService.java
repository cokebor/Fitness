package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Pais;

public interface PaisService {
	public List<Pais> listaPaises();
	
	public Pais guardar(Pais pais);
	
	public void eliminar(Integer idPais);
	
	public Pais encontrarPais(Integer idPais);
}

