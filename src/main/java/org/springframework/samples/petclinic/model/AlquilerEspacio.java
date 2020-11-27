package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "alquilerEspacio")
public class AlquilerEspacio extends BaseEntity{

    @Column(name = "precio")
    @NotEmpty
    protected Integer precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lugar_realizacion", referencedColumnName = "id")
    private LugarRealizacion lugarRealizacion;
     
}