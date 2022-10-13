package com.fitness.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "itemsComprobantes")
@Data
public class ItemComprobante implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IdComprobante", nullable=false)
    @Id
    private Comprobante Comprobante;
    
    @Id
    private int Renglon;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name = "IdProducto")
    private Producto Producto;
    
    @Column(name="Cantidad" , nullable = false)
    private int Cantidad;
    
    @Column(name="PrecioUnitario" , nullable = false)
    private float PrecioUnitario;
    
    public float getSubTotal() {
    	return Cantidad*PrecioUnitario;
    }
    
    private static final long serialVersionUID = 1L;
}
