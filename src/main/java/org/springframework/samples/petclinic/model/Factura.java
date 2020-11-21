package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "factura")
public class Factura extends NamedEntity{
    @Column(name="precioTotal")
    @NotEmpty
    protected Double precioTotal;
    @Column(name="usuarioAsociado")
    @NotEmpty
    protected String usuarioAsocidado;
    @Column(name = "fechaFactura")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFactura;
}
