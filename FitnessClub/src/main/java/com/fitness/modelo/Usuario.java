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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdUsuario")
	private int IdUsuario;
	@Column(name="Nombre" , length = 30, nullable = false)
	private String Nombre;
	@Column(name="apellido" , length = 30, nullable = false)
	private String Apellido;
	@Column(name="mail" , length = 30, nullable = false)
	private String Mail;
	@Column(name="Direccion" , length = 30, nullable = false)
	private String Direccion;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdLocalidad")
	private Localidad Localidad;
	
	private int DNI;
	@Column(name="Password" , length = 30, nullable = false)
	private String Password;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdRol")
	private Rol Rol;
	@Column(name="Estado" , nullable = false)
	private Boolean Estado;
}
