package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Provincia;


public interface ProvinciaService {
	public List<Provincia> listaProvincias();
	
	public void guardar(Provincia provincia);
	
	public void eliminar(Provincia provincia);
	public Provincia encontrarProvincia(Provincia provincia);
}
