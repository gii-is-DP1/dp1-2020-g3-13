package org.springframework.samples.petclinic.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "lugar_realizacion")
public class LugarRealizacion extends BaseEntity{

<<<<<<< Updated upstream
=======
// CAMBIARLO POR LA ID JOIN COLUMN MAPPED BY EN ACTIVIDADES EN LUGARREALIZACION

>>>>>>> Stashed changes
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarRealizacion")
    private List<Actividad> actividades;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarRealizacion")
    private List<AlquilerEspacio> alquilerEspacio;

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

    @Column(name = "caracteristicas")
    @NotEmpty
    private String caracteristicas;

    @Column(name = "url_foto")
    private String urlFoto;
    
    @Column(name = "precioDia")
    private Double precio;



}
