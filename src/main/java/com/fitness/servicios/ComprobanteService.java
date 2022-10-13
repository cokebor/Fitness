package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Comprobante;

public interface ComprobanteService {
	public List<Comprobante> listaComprobantes();
	
	public Comprobante guardar(Comprobante comprobante);
	
	public void eliminar(Integer idComprobante);
	public Comprobante encontrarComprobante(Integer idComprobante);
}
