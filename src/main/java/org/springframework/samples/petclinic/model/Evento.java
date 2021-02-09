package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "eventos")
public class Evento extends BaseEntity {
    @Column(name = "tipoEvento")
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column(name = "descripcion")
    @NotBlank(message = "La descripción debe estar comprendida entre 65 y 150 caracteres, además de no estar vacía")
    @Size(min = 65, max = 150, message = "La descripción debe estar comprendida entre 65 y 150 caracteres, además de no estar vacía")
    private String descripcion;

    @Column(name = "nombreEvento")
    @NotBlank(message = "El nombre del evento debe estar comprendido entre 10 y 100 caracteres, además de no estar vacío")
    @Size(min = 10, max = 100, message = "El nombre del evento debe estar comprendido entre 10 y 100 caracteres, además de no estar vacío")
    private String nombreEvento;

    @Column(name = "fechaInicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;

    @Column(name = "esPublico")
    private Boolean esPublico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;
}