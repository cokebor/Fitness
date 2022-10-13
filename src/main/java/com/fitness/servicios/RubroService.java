package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Rubro;

public interface RubroService {
	public List<Rubro> listaRubros();
	
	public Rubro guardar(Rubro rubro);
	
	public void eliminar(Integer idRubro);
	public Rubro encontrarRubro(Integer idRubro);

}
