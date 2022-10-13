package com.fitness.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

@Entity
@Table(name = "comprobantes")
@Data
public class Comprobante implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdComprobante")
	private int IdComprobante;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Fecha")
	private Date Fecha;
	
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdFormadepago")
	private FormaDePago FormaDePago;
	@ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdUsuario")
	private Usuario Usuario;
	@Column(name = "Total")
	private float Total;
	
	@OneToMany(mappedBy="Comprobante")
	private List<ItemComprobante> item;
	
	public int getIdComprobante() {
		return IdComprobante;
	}
	public void setIdComprobante(int idComprobante) {
		IdComprobante = idComprobante;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public FormaDePago getFormaDePago() {
		return FormaDePago;
	}
	public void setFormaDePago(FormaDePago formaDePago) {
		FormaDePago = formaDePago;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	private static final long serialVersionUID = 1L;
	
}
