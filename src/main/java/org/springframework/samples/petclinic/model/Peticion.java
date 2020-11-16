package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name = "peticion")
public class Peticion extends NamedEntity{
    @Column(name = "email")
    @NotEmpty
    private String email;
    @Column(name = "organizacion")
    @NotEmpty
    private String organizacion;
    @Column(name = "cif")
    @NotEmpty
    private String cif;
    @Column(name = "info")
    @NotEmpty
    private String info;

}
