package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name= "organizaciones")  
public class Organizacion extends BaseEntity {    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    @Column(name = "nombreOrganizacion")
    @NotEmpty
    private String nombreOrganizacion;

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
