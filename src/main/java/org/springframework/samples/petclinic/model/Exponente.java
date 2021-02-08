package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exponente")
public class Exponente extends BaseEntity{


    
    @Column(name = "nombreExponente")
    @NotBlank(message = "El nombre del exponente no puede estar vacío y debe estar comprendido entre 2 y 20 caracteres")
    @Size(min = 3, max = 20, message = "El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 20 caracteres")
    private String nombreExponente;

    @Column(name = "apellidosExponente")
    @NotBlank(message = "Los apellidos del exponente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    @Size(min = 3, max = 35, message = "El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    private String apellidosExponente;
    
    @Column(name = "alias")
    private String alias;
    
}
