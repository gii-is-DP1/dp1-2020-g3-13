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

@Entity
@Data
@Table(name="consulta")
public class Consulta extends NamedEntity{

    @Column(name = "asunto")
    @NotEmpty
    protected String asunto;
    
    @Column(name = "descripcion")
    @NotEmpty
    protected String descripcion;

    @Column(name = "fechaConsulta")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaConsulta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_evento", referencedColumnName = "nombreEvento")
    private Evento evento;

}
