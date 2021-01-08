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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.FechaFinActividadRestriccion;
import org.springframework.samples.petclinic.model.Validadores.FechaInicioRestriccion;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Data
@Getter
@Setter
@Entity
@Table(name = "actividad")
// Comprueba que la fecha de fin no es anterior a la de inicio
@FechaFinActividadRestriccion(field = "fechaInicio", fieldMatch = "fechaFin", message = "La fecha de fin es anterior a la de inicio")
public class Actividad extends BaseEntity {

  @ManyToMany(cascade = CascadeType.ALL)
  private List<Exponente> exponentes;

  @NotBlank
  @Size(min = 2, max = 30, message = "El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío")
  @Column(name = "tematicaActividad")
  protected String tematicaActividad;

  @NotBlank
  @Size(min = 15, max = 400, message = "La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío" )
  @Column(name = "descripcionActividad")
  protected String descripcionActividad;
  // Comprueba que la fecha de inicio no es anterior a la actual
  @FechaInicioRestriccion
  @Column(name = "fechaInicio")
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private LocalDate fechaInicio;

  @Column(name = "fechaFin")
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
