package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "actividad")
public class Actividad extends BaseEntity{

    @Column(name = "tematicaActividad")
    @NotEmpty
    protected String tematicaActividad;

    @Column(name = "descripcionActividad")
    @NotEmpty
    protected String descripcionActividad;
    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;
    @Column(name ="fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;
// CAMBIARLO POR LA ID JOIN COLUMN MAPPED BY EN ACTIVIDADES EN LUGARREALIZACION
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_recinto", referencedColumnName = "nombre_recinto")
	private LugarRealizacion lugarRealizacion;

}
