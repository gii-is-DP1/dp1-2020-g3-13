package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exponente")
public class Exponente extends BaseEntity{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actividad> actividades;
    
    @Column(name = "nombreExponente")
    @NotEmpty
    protected String nombreExponente;
    @Column(name = "apellidosExponente")
    @NotEmpty
    protected String apellidosExponente;
    @Column(name = "alias")
    protected String alias;
    
}
