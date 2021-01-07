package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Entity
@Data
@Table(name = "tipoentradas")
public class TipoEntrada extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;
    
    @Column(name = "precio")
    @NotNull
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    @NotNull
    private NombreTiposEntrada nombre;

    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd' 'HH:mm")
    private LocalDateTime fechaInicio;

    // @Column(name = "descuento")
    // @NotEmpty
    // protected Integer descuento;

    @Column(name = "fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd' 'HH:mm")
    private LocalDateTime fechaFin;

    @Column(name = "numEntradas")
    @NotNull
    protected Integer numEntradas;

       
}