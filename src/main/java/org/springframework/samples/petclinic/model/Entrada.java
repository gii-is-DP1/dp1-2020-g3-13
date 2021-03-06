package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.Validadores.DNIRestriccion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrada")
public class Entrada extends BaseEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_tipoEntrada", referencedColumnName = "id")
    private TipoEntrada tipoEntrada;

    @Column(name = "nombreAsistente")
    @NotBlank(message = "El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    @Size(min = 3, max = 35, message = "El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    private String nombreAsistente;

    @Column(name = "dni")
    @DNIRestriccion
    private String dni;
}
