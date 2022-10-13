package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.FormaDePago;

public interface FormaDePagoService {
	public List<FormaDePago> listaFormasDePago();
	
	public FormaDePago guardar(FormaDePago formaDePago);
	
	public void eliminar(Integer idFormaDePago);
	public FormaDePago encontrarFormaDePago(Integer idFormaDePago);

}
