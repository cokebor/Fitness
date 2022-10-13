package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Provincia;


public interface ProvinciaService {
	public List<Provincia> listaProvincias();
	
	public Provincia guardar(Provincia provincia);
	
	public void eliminar(Integer idProvincia);
	public Provincia encontrarProvincia(Integer idProvincia);
}
