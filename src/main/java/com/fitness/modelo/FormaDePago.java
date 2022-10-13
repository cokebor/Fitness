package com.fitness.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "formasdepago")
@Data
public class FormaDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdFormadepago")
	private int IdFormaDePago;	
	
	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;

	public int getIdFormaDePago() {
		return IdFormaDePago;
	}

	public void setIdFormaDePago(int idFormaDePago) {
		IdFormaDePago = idFormaDePago;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
