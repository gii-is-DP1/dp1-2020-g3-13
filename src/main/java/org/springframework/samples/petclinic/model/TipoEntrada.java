package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Entity
@Data
@Table(name = "tipoentradas")
public class TipoEntrada extends BaseEntity{

    @Column(name = "precio")
    @NotEmpty
    protected Integer precio;

    @Column(name = "nombre")
    @NotEmpty
    protected String nombre;

    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;

    @Column(name = "descuento")
    @NotEmpty
    protected Integer descuento;

    @Column(name = "fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;

    @Column(name = "numEntradas")
    @NotEmpty
    protected Integer numEntradas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;
       
}