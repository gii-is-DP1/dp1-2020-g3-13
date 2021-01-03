package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "lineaFactura")
public class LineaFactura extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    private TipoEntrada tipoEntrada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entrada", referencedColumnName = "id")
    private Entrada entrada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alquilerEspacio", referencedColumnName = "id")
    private AlquilerEspacio alquilerEspacio;

    @Column(name = "precio")
    protected Double precio;

    @Column(name = "cantidad")
    @NotNull
    protected Integer cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura", referencedColumnName = "id")
    private Factura factura;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrito", referencedColumnName = "id")
    private Carrito carrito;
}