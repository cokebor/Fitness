package com.fitness.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
