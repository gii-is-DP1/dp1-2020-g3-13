package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "consulta")
public class Consulta extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @NotBlank(message = "El asunto no puede estar vacio")
    @Size(max = 30, message = "El asunto no debe superar los 30 caracteres")
    @Column(name = "asunto")
    protected String asunto;

    @Column(name = "descripcion")
    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 15, max = 400, message = "La descripción debe estar comprendida entre 15 y 400 caracteres")
    protected String descripcion;

    @Column(name = "fechaConsulta")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaConsulta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

}
