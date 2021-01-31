package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
<<<<<<< Updated upstream
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
=======
import javax.validation.constraints.NotBlank;
>>>>>>> Stashed changes
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
<<<<<<< Updated upstream
import lombok.Getter;
import lombok.Setter;
=======
import lombok.Data;
>>>>>>> Stashed changes



@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente extends Persona{

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cliente")
    private  List<Entrada> entradas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventosFavoritos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    @Valid
    private Usuario usuario;


    @Column(name = "telefono")
    @NotBlank(message = "El telefono no puede estar vacio")
    @Size(min=9, max= 9, message = "El telefono debe ser exactamente de 9 caracteres")
    private String telefono;

      
}
