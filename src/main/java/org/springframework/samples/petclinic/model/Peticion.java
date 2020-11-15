package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Data;
@Entity
@Data
public class Peticion extends NamedEntity{
    String email;
    String nombreOrganizacion;
    String cif;
    String info;
}
