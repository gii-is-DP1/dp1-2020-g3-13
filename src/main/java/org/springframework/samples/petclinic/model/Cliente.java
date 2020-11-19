package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Persona{
    Integer telefono;
      
}
