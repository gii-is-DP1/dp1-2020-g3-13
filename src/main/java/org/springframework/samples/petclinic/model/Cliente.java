package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;


import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Persona{
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cliente")
   private  List<VentaEntrada> entradas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;
    


    @Column(name = "telefono")
    @NotNull
    @Range(min=100000000,max = 999999999, message = "Número de teléfono no válido   ")
    protected Integer telefono;

      
}
