package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Entity
@Data
@Table(name = "eventos")
public class Evento extends NamedEntity{

    @Column(name = "tipoEvento")
    @NotEmpty
    protected String tipoevento;

    @Column(name = "descripcion")
    @NotEmpty
    protected String descripcion;

    @Column(name = "nombreEvento")
    @NotEmpty
    protected String nombreEvento;

    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;

    @Column(name = "medidasSanitarias")
    @NotEmpty
    protected String medidasSanitarias;

    @Column(name = "categoria")
    @NotEmpty
    protected String categoria;

    @Column(name = "fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;

       
}