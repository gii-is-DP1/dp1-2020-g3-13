package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@MappedSuperclass
@Data
public class Persona extends BaseEntity{
    @Column(name = "nombre")
    @NotEmpty
    protected String nombre;
    @Column(name = "apellidos")
    @NotEmpty
    protected String apellidos;
    @Column(name = "email")
    @NotEmpty
    protected String email;

}
