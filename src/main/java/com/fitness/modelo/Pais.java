package com.fitness.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "paises")
@Data
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdPais")
	private int IdPais;	
	
	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;

	public int getIdPais() {
		return IdPais;
	}

	public void setIdPais(int idPais) {
		IdPais = idPais;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
	
}
