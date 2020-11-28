package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity
@Table(name = "exponente")
public class Exponente extends BaseEntity{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actividad> actividades;
    
    @Column(name = "nombreExpo")
    @NotEmpty
    protected String nombreExponente;
    
}
