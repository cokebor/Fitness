package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.FormaDePago;
import com.fitness.repositorios.FormaDePagoRepository;
@Service
public class FormaDePagoServiceImpl implements FormaDePagoService{

	@Autowired
	private FormaDePagoRepository formaDePagoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<FormaDePago> listaFormasDePago() {
		return (List<FormaDePago>)formaDePagoRepository.findAll();
	}

	@Override
	@Transactional
	public FormaDePago guardar(FormaDePago formaDePago) {
		return formaDePagoRepository.save(formaDePago);
	}

	@Override
	@Transactional
	public void eliminar(Integer idFormaDePago) {
		formaDePagoRepository.deleteById(idFormaDePago);
	}

	@Override
	@Transactional(readOnly = true)
	public FormaDePago encontrarFormaDePago(Integer idFormaDePago) {
		return formaDePagoRepository.findById(idFormaDePago).orElse(null);
	}
}
