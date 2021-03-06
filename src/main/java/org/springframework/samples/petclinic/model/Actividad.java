package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.ActividadParaEventoRestriccion;
import org.springframework.samples.petclinic.model.Validadores.FechaInicioTimeRestriccion;
import org.springframework.samples.petclinic.model.Validadores.FechasActividadRestriccion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actividad")
// Comprueba que la fecha de fin no es anterior a la de inicio
@FechasActividadRestriccion(fechaInicio = "fechaInicio", fechaFin = "fechaFin")
@ActividadParaEventoRestriccion(fechaInicio = "fechaInicio", fechaFin = "fechaFin", evento = "evento")
public class Actividad extends BaseEntity {

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "exponente_id", referencedColumnName = "id")
  private List<Exponente> exponentes;

  @NotBlank(message = "El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío")
  @Size(min = 2, max = 30, message = "El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío")
  @Column(name = "tematicaActividad")
  private String tematicaActividad;

  @NotBlank(message = "La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío")
  @Size(min = 15, max = 400, message = "La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío" )
  @Column(name = "descripcionActividad")
  private String descripcionActividad;
  // Comprueba que la fecha de inicio no es anterior a la actual
  @FechaInicioTimeRestriccion
  @Column(name = "fechaInicio")
  @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
  private LocalDateTime fechaInicio;

  @Column(name = "fechaFin")
  @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
  private LocalDateTime fechaFin;
  

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "alquiler_espacio_id", referencedColumnName = "id")
  private AlquilerEspacio alquilerEspacio;

  @ManyToOne(optional = false)
  @JoinColumn(name = "evento_id", referencedColumnName = "id")
  private Evento evento;

}
