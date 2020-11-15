package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Data;
@Entity
@Data
public class Peticion extends NamedEntity{
    private String email;
    private String nombreOrganizacion;
    private String cif;
    private String info;
}
