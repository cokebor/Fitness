package com.fitness.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.modelo.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
