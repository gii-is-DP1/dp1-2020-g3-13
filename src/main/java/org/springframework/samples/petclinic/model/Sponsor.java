package org.springframework.samples.petclinic.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name= "sponsor")
public class Sponsor extends BaseEntity {


    @ManyToOne(cascade = CascadeType.ALL)
    private Evento evento;

    @Column(name = "empresa")
    @NotEmpty
    private String empresa;


    @Column(name = "urlWeb")
    @NotEmpty
    private String urlWeb;

    @Column(name = "urlLogo")
    @NotEmpty
    private String urlLogo;

    @Column(name = "categoria")
    @NotEmpty
    private String categoria;



}