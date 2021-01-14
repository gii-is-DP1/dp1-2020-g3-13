package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name= "organizaciones")  
public class Organizacion extends BaseEntity {    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacion")
    private List<Evento> eventos;

    @Column(name = "nombreOrganizacion")
    @NotEmpty
    private String nombreOrganizacion;

    @Email(message = "El formato del email no es válido")
    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "cif")
    @NotEmpty
    private String cif;
    @Column(name = "info")
    @NotEmpty
    private String info;


    
}
