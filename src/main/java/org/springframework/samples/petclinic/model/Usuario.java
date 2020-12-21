package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "nombreUsuario") 
    @NotEmpty(message = "no puede estar vacio")
    private String nombreUsuario;
    @NotEmpty
    protected String password;
	boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Autoridades autoridades;
}
