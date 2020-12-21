package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@MappedSuperclass
@Data
public class Persona extends BaseEntity{
    @Column(name = "nombre")
    @Size(min = 2, message = "El nombre es demasiado corto")
    @Size(max = 30, message = "El nombre es demasiado largo")
    protected String nombre;
    @Column(name = "apellidos")
    @Size(min = 2, message = "Los apellidos son demasiado cortos")
    @Size(max = 80, message = "Los apellidos son demasiado largos")
    protected String apellidos;
    @Column(name = "email")
    @Email(message = "El formato del email no es válido")
    @NotEmpty
    protected String email;

}
