package com.fitness.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.modelo.Comprobante;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

}
