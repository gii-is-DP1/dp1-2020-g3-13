package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Data
@Entity
@Table(name = "peticion")
public class Peticion extends BaseEntity{

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
    @Column(name = "fecha")
    // @DateTimeFormat(pattern = "yyyy/MM/dd")
    protected LocalDate fecha;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_admin", referencedColumnName = "nombre_usuario")
    private Admin admin;
}
