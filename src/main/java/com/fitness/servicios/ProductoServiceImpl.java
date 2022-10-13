package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Producto;
import com.fitness.repositorios.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
    private ProductoRepository productoRepository;
	@Override
	@Transactional(readOnly = true)
	public List<Producto> listaProductos() {
	    return (List<Producto>) productoRepository.findAll();
	}

	@Override
	@Transactional
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void eliminar(Integer idProducto) {
		productoRepository.deleteById(idProducto);	
	}

	@Override
	@Transactional(readOnly = true)
	public Producto encontrarProducto(Integer idProducto) {
		// TODO Auto-generated method stub
		return productoRepository.findById(idProducto).orElse(null);
	}
}
