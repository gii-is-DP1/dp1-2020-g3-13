package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "peticion")
public class Peticion extends BaseEntity{

  //@OneToOne(cascade = CascadeType.ALL, mappedBy = "organizacion")
  //private Organizacion organizacion;

    @Column(name = "email")
    @Email(message = "El formato del email no es válido")
    @NotEmpty
    protected String email;
    @Column(name = "nombre_organizacion")
    @NotEmpty
    @Size(min = 2, max = 16)
    protected String nombre_organizacion;
    @Column(name = "cif")
    @NotEmpty
    @Size(min = 2, max = 12)
    protected String cif;
    @Column(name = "info")
    @NotEmpty
    @Size(min = 2, max = 255)
    protected String info;
    @Column(name = "fecha")

    protected LocalDate fecha;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_admin", referencedColumnName = "nombre_usuario")
    private Admin admin;
}