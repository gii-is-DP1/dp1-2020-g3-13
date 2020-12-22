package org.springframework.samples.petclinic.model;

import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
@Table(name = "carrito")
public class Carrito extends BaseEntity{
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaFactura> lineasFacturas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "total")
    private Double total;
    
}
