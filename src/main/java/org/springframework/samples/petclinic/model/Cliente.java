package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente extends Persona{

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Evento> eventosFavoritos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    @Valid
    private Usuario usuario;


    @Column(name = "telefono")
    @Digits(fraction = 0, integer= 9, message = "El telefono no puede estar vacio, y debe ser exactamente de 9 caracteres" )
    @Length(min =9,message = "El telefono no puede estar vacio, y debe ser exactamente de 9 caracteres")
    private String telefono;

      
}
