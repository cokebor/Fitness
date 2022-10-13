package com.fitness.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "rubros")
@Data
public class Rubro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdRubro")
	private int IdRubro;	
	
	@Column(name="Descripcion" , length = 60, nullable = false)
	private String Descripcion;

	public int getIdRubro() {
		return IdRubro;
	}

	public void setIdRubro(int idRubro) {
		IdRubro = idRubro;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
