package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class Persona extends BaseEntity{
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
}
