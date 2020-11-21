package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    String nombreUsuario;

    String password;
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Autoridades> autoridades;
}
