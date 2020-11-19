package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name= "organizaciones")  
public class Organizacion extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "organizacion")
    private Peticion peticion;
    @Column(name = "usuario")
    @NotEmpty
    private String user;
    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "nombreOrganizacion")
    @NotEmpty
    protected String nombreOrganizacion;
    @Column(name = "cif")
    @NotEmpty
    private String cif;
    @Column(name = "info")
    @NotEmpty
    private String info;


    
}
