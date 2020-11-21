package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;



@Data
@Entity
@Table(name = "peticion")
public class Peticion extends NamedEntity{

  //@OneToOne(cascade = CascadeType.ALL, mappedBy = "organizacion")
  //private Organizacion organizacion;

    @Column(name = "email")
    @NotEmpty
    protected String email;
    @Column(name = "nombre_organizacion")
    @NotEmpty
    protected String nombre_organizacion;
    @Column(name = "cif")
    @NotEmpty
    protected String cif;
    @Column(name = "info")
    @NotEmpty
    protected String info;

}
