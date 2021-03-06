package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends Persona{
    @OneToOne(optional = false)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

}
