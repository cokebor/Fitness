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
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "localidades")
@Data
@Setter
@Getter
public class Localidad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdLocalidad")
	private int IdLocalidad;
	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdProvincia")
	private Provincia Provincia;
}
