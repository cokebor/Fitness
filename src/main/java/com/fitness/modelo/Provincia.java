package com.fitness.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "provincias")
@Data
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdProvincia")
	private int IdProvincia;
	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdPais")
	private Pais Pais;
	public int getIdProvincia() {
		return IdProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		IdProvincia = idProvincia;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Pais getPais() {
		return Pais;
	}
	public void setPais(Pais pais) {
		Pais = pais;
	}
	
	
}
