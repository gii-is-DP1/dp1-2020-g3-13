package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.DescripcionActividadRestriccion;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Data
@Getter
@Setter
@Entity
@Table(name = "actividad")
public class Actividad extends BaseEntity{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Exponente> exponentes;

    @Column(name = "tematicaActividad")
    @NotEmpty
    protected String tematicaActividad;
    //Comprueba que la descripcion de la actividad no sea vacía y esté entre 30 y 400 caracteres
    @DescripcionActividadRestriccion
    protected String descripcionActividad;
    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;
    @Column(name ="fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;
// CAMBIARLO POR LA ID JOIN COLUMN MAPPED BY EN ACTIVIDADES EN LUGARREALIZACION
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_realizacion_id", referencedColumnName = "id")
    private LugarRealizacion lugarRealizacion;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

}
