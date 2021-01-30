package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alquilerEspacio")
public class AlquilerEspacio extends BaseEntity{

    @Column(name = "precioTotal")
    // @NotEmpty
    protected Double precioTotal;

    @Column(name = "fechaInicioReserva")
    // @NotEmpty
    protected LocalDateTime fechaInicioReserva;

    @Column(name = "fechaFinReserva")
    // @NotEmpty
    protected LocalDateTime fechaFinReserva;

    @ManyToOne()
    @JoinColumn(name = "id_lugar_realizacion", referencedColumnName = "id")
    private LugarRealizacion lugarRealizacion;
     
}