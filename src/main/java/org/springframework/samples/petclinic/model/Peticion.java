package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "peticion")
public class Peticion extends BaseEntity {

  @Column(name = "email")
  @NotBlank(message = "El formato del email no es válido")
  @Email(message = "El formato del email no es válido")
  private String email;

  @Column(name = "nombre_organizacion")
  @Size(min = 2, max = 30, message = "El nombre de la organización no puede estar en blanco, ser menor de 3 Caracteres ni mayor que 30")
  private String nombre_organizacion;

  @Column(name = "cif")
  @Pattern(regexp = "^[aA-zZ]{1}[0-9]{7,8}$")
  private String cif;

  @Column(name = "info")
  @Size(min = 2, max = 255, message = "La información proporcionada debe ser mayor a 30 caracteres y menor a 255, escriba una breve descripción de su negocio")
  private String info;

  @Column(name = "fecha")
  private LocalDate fecha;

}