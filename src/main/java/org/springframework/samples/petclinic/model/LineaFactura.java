package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "lineaFactura")
public class LineaFactura extends NamedEntity{

    @Column(name = "precio")
    @NotEmpty
    protected Integer precio;

    @Column(name = "cantidad")
    @NotEmpty
    protected Integer cantidad;

     
}