package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "factura")
public class Factura extends BaseEntity{
    @Column(name="precioTotal")
    @NotEmpty
    private Double precioTotal;
    @Column(name="usuarioAsociado")
    @NotEmpty
    private String usuarioAsocidado;
    @Column(name = "fechaFactura")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFactura;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaFactura> lineasFacturas;
}
