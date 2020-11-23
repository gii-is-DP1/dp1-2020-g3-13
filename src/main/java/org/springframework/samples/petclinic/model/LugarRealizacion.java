package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "lugar_realizacion")
public class LugarRealizacion extends BaseEntity{
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Actividad> actividades;
    
    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "aforo")
    private Integer  aforo;

    @Column(name = "nombre_recinto")
    private String nombre_recinto;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email")
    private String email;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "caracteristicas")
    private String caracteristicas;

    @Column(name = "url_foto")
    private String urlFoto;



}
