package com.fitness.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Table(name = "roles")
@Data
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdRol")
	private int IdRol;
	@Column(name="nombre" , length = 30, nullable = false)
	private String Descripcion;
}
