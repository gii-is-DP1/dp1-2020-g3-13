package org.springframework.samples.petclinic.model;

import javax.persistence.Table;
import javax.validation.constraints.Min;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito extends BaseEntity{
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaFactura> lineasFacturas;

    @OneToOne()
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;
    @OneToOne()
    @JoinColumn(name = "id_organizacion", referencedColumnName = "id")
    private Organizacion organizacion;

    @Column(name = "total")
    @Min(value = 0)
    private Double total;
    
}
