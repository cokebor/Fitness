package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Comprobante;
import com.fitness.repositorios.ComprobanteRepository;
@Service
public class ComprobanteServiceImpl implements ComprobanteService {

	@Autowired
    private ComprobanteRepository comprobanteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Comprobante> listaComprobantes() {
		return (List<Comprobante>)comprobanteRepository.findAll();
	}

	@Override
	@Transactional
	public Comprobante guardar(Comprobante comprobante) {
		return comprobanteRepository.save(comprobante);
	}

	@Override
	@Transactional
	public void eliminar(Integer idComprobante) {
		comprobanteRepository.deleteById(idComprobante);
	}

	@Override
	@Transactional(readOnly = true)
	public Comprobante encontrarComprobante(Integer idComprobante) {
		return comprobanteRepository.findById(idComprobante).orElse(null);
	}
}
