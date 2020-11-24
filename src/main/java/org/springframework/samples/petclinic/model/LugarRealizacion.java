package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "lugar_realizacion")
public class LugarRealizacion extends BaseEntity{
    @OneToMany(cascade = CascadeType.ALL)
    private List<Actividad> actividades;
    
    @Column(name = "telefono")
    @NotNull
    private Integer telefono;

    @Column(name = "aforo")
    @NotNull
    private Integer  aforo;

    @Column(name = "nombre_recinto")
    @NotEmpty
    private String nombre_recinto;

    @Column(name = "direccion")
    @NotEmpty
    private String direccion;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "caracteristicas")
    @NotEmpty
    private String caracteristicas;

    @Column(name = "url_foto")
    private String urlFoto;



}