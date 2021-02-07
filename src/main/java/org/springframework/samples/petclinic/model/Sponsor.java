package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sponsor")
public class Sponsor extends BaseEntity {

    @Column(name = "empresa")
    @Size(min = 2, max = 30, message = "El nombre de la empresa no puede ser mayor a 30 caracteres ni menor a 2")
    private String empresa;

    @Column(name = "urlWeb")
    @NotBlank(message = "Debe introducir una Url válida para la web")
    @URL(message = "Debe introducir una Url válida para la web")
    private String urlWeb;

    @Column(name = "urlLogo")
    @NotBlank(message = "Debe introducir una Url válida para la web")
    @URL(message = "Debe introducir una Url válida para el logo")
    private String urlLogo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "evento_id", referencedColumnName = "id")
    private Evento evento;

}