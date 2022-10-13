package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Producto;

public interface ProductoService {
	public List<Producto> listaProductos();
	
	public Producto guardar(Producto producto);
	
	public void eliminar(Integer idProducto);
	
	public Producto encontrarProducto(Integer idProducto);
}
