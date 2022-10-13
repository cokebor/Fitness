package com.fitness.servicios;

import java.util.List;

import com.fitness.modelo.Disciplina;

public interface DisciplinaService {
	public List<Disciplina> listaDisciplinas();
	
	public Disciplina guardar(Disciplina disciplina);
	
	public void eliminar(Integer idDisciplina);
	public Disciplina encontrarDisciplina(Integer idDisciplina);
}
