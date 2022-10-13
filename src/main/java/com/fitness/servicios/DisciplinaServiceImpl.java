package com.fitness.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitness.modelo.Disciplina;
import com.fitness.repositorios.DisciplinaRepository;
@Service
public class DisciplinaServiceImpl implements DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Disciplina> listaDisciplinas() {
		// TODO Auto-generated method stub
		return (List<Disciplina>) disciplinaRepository.findAll();
	}

	@Override
	@Transactional
	public Disciplina guardar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	@Override
	@Transactional
	public void eliminar(Integer idDisciplina) {
		disciplinaRepository.deleteById(idDisciplina);
	}

	@Override
	@Transactional(readOnly = true)
	public Disciplina encontrarDisciplina(Integer idDisciplina) {
		return disciplinaRepository.findById(idDisciplina).orElse(null);
	}
}
