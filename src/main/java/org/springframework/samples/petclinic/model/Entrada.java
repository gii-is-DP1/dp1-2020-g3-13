package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "entrada")
public class Entrada extends BaseEntity{


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    private TipoEntrada tipoEntrada;

    @OneToOne(cascade = CascadeType.ALL)
    private LineaFactura lineaFactura;

    @OneToOne(cascade = CascadeType.ALL)
    private VentaEntrada ventaEntrada;

    @Column(name = "nombreAsistente")
    @NotEmpty
    private String nombreAsistente;

    @Column(name = "dni")
    @NotEmpty
    private String dni;
}
