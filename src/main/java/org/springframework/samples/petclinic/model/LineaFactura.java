package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "lineaFactura")
public class LineaFactura extends BaseEntity{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta_entrada", referencedColumnName = "id")
    private VentaEntrada ventaEntrada;
    @Column(name = "precio")
    @NotEmpty
    protected Integer precio;

    @Column(name = "cantidad")
    @NotEmpty
    protected Integer cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura", referencedColumnName = "id")
    private Factura factura;
}