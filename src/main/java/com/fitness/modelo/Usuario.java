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
	@Column(name="Nombre" , length = 45, nullable = false)
	private String Nombre;
	@Column(name="apellido" , length = 45, nullable = false)
	private String Apellido;
	@Column(name="mail" , length = 45, nullable = false)
	private String Mail;
	@Column(name="Direccion" , length = 70, nullable = false)
	private String Direccion;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdLocalidad")
	private Localidad Localidad;
	
	private int DNI;
	@Column(name="Password" , length = 45, nullable = false)
	private String Password;
	
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdRol")
	private Rol Rol;
	@Column(name="Estado" , nullable = false)
	private Boolean Estado;
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public Localidad getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(Localidad localidad) {
		Localidad = localidad;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Rol getRol() {
		return Rol;
	}
	public void setRol(Rol rol) {
		Rol = rol;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
	
}
