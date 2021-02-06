package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "lugar_realizacion")
public class LugarRealizacion extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarRealizacion")
    private List<AlquilerEspacio> alquilerEspacio;

    @Column(name = "telefono")
    @Digits(fraction = 0, integer = 9, message = "El número de teléfono debe tener 6 dígitos")
    @Length(min = 16, message ="El número de teléfono debe tener 6 dígitos" )
    private String telefono;

    @Column(name = "aforo")
    @NotNull
    @Range(min = 1, max = 1000000)
    private Integer  aforo;

    @Column(name = "nombre_recinto")
    @NotBlank(message = "El nombre del recinto no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    @Size(min = 3, max = 35, message = "El nombre del recinto no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres")
    private String nombre_recinto;

    @Column(name = "direccion")
    @NotBlank(message = "La dirección no puede estar vacía y debe estar comprendida entre 5 y 35 caracteres")
    @Size(min = 5, max = 35, message = "La dirección no puede estar vacía y debe estar comprendida entre 5 y 35 caracteres")
    private String direccion;

    @Column(name = "email")
    @NotEmpty
    @Email(message = "El formato del email es incorrecto, compruebe si contiene una @ y si acaba en .dominio")
    private String email;

    @Column(name = "caracteristicas")
    @NotBlank(message = "El campo de características no puede estar vacío y debe estar comprendido entre 15 y 65 caracteres")
    @Size(min = 15, max = 218, message = "El campo de características no puede estar vacío y debe estar comprendido entre 15 y 65 caracteres")
    private String caracteristicas;

    @Column(name = "url_foto")
    private String urlFoto;
    
    @Column(name = "precioDia")
    @NotNull
    private Double precio;



}
