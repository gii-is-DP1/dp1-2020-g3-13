package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.ActividadAforoYNumeroEntradasRestriccion;
import org.springframework.samples.petclinic.model.Validadores.FechaTipoEntradaRestriccion;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipoentradas")

@ActividadAforoYNumeroEntradasRestriccion(actividades = "actividades", numEntradas = "numEntradas")
//@ActividadesParaTipoEntradaRestriccion(fechaInicio = "fechaInicio", fechaFin = "fechaFin", actividades = "actividades")
@FechaTipoEntradaRestriccion(
    evento_id = "evento_id",
    fechaInicio = "fechaInicio", fechaFin = "fechaFin", nombreEntrada = "nombre", message = "La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento")
public class TipoEntrada extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @ManyToMany
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    private List<Actividad> actividades;

    @Column(name = "precio")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "El precio debe ser superior o igual a 0")
    private Double precio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private NombreTiposEntrada nombre;

    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd' 'HH:mm")
    private LocalDateTime fechaInicio;

    @Column(name = "fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd' 'HH:mm")
    private LocalDateTime fechaFin;

    @Column(name = "numEntradas")
    @Min(value = 1, message = "Debe tener al menos 1 entrada disponible para el evento")
    protected Integer numEntradas;



}