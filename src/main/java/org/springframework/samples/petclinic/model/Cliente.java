package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import javax.persistence.Column;


import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Persona{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;
    


    @Column(name = "telefono")
    @NotNull
    protected Integer telefono;

      
}
