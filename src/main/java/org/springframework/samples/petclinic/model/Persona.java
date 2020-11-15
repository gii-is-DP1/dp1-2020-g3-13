package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Persona extends BaseEntity{
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
}
