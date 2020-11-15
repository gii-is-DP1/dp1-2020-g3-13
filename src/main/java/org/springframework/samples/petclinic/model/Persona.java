package org.springframework.samples.petclinic.model;



import javax.persistence.Entity;



import lombok.Data;
@Entity
@Data
public class Persona extends NamedEntity{
    String usuario;
    String password;
    String nombre;
    String apellidos;
    String email;
}
