package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "organizaciones")
public class Organizacion extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacion")
    private List<Evento> eventos;

    @Column(name = "nombreOrganizacion")
    @NotBlank(message = "El nombre de la organización no puede estar en blanco, ser menor de 3 Caracteres ni mayor que 30")
    @Size(min = 2, max = 30, message = "El nombre de la organización no puede estar en blanco, ser menor de 3 Caracteres ni mayor que 30")
    private String nombreOrganizacion;

    @Column(name = "email")
    @Email(message = "El formato del email no es válido")
    private String email;

    @Column(name = "cif")

    private String cif;
    @Column(name = "info")
    @NotEmpty
    private String info;

}
