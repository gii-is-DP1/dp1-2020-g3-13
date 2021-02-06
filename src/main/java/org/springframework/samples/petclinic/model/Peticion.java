package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.Validadores.ValidadorCIFRestriccion;

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
    @NotBlank(message = "El formato del email no es válido")
    @Email(message = "El formato del email no es válido")
    protected String email;

    @Column(name = "nombre_organizacion")
    @Size(min = 2, max = 30, message = "El nombre de la organización no puede estar en blanco, ser menor de 3 Caracteres ni mayor que 30")
    protected String nombre_organizacion;
    
    @Column(name = "cif")
    @ValidadorCIFRestriccion
    protected String cif;

    @Column(name = "info")
    @Size(min = 2, max = 255, message = "La información proporcionada debe ser mayor a 30 caracteres y menor a 255, escriba una breve descripción de su negocio")
    protected String info;

    @Column(name = "fecha")
    protected LocalDate fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_admin", referencedColumnName = "nombre_usuario")
    private Admin admin;
}