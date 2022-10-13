package com.fitness.modelo;

import java.sql.Blob;

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
@Table(name = "productos")
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdProducto")
	private int IdProducto;

	@Column(name="Descripcion" , length = 45, nullable = false)
	private String Descripcion;
	
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdRubro")
	private Rubro rubro;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdDisciplina")
	private Disciplina disciplina;
	
	@Column(name="precioUnitario", nullable = false)
	private float precioUnitario;
	
	@Column(name="imagen", nullable = false)
	private Blob imagen;
	
	@Column(name="stock", nullable = false)
	private int stock;
	
	@Column(name="esServicio", nullable = false)
	private Boolean esServicio;
	
	@Column(name="estado", nullable = false)
	private Boolean estado;

	public int getIdProducto() {
		return IdProducto;
	}

	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Boolean getEsServicio() {
		return esServicio;
	}

	public void setEsServicio(Boolean esServicio) {
		this.esServicio = esServicio;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
