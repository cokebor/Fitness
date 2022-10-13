package com.fitness.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
