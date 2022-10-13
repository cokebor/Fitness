package com.fitness.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "disciplinas")
@Data
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdDisciplina")
	private int IdDisciplina;	
	
	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;

	public int getIdDisciplina() {
		return IdDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		IdDisciplina = idDisciplina;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}
