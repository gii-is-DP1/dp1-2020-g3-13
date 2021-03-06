package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;
import javax.persistence.OneToOne;
@Getter
@Setter 
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @Column(name = "nombreUsuario") 
    @NotEmpty(message = "El nombre de usuario debe estar comprendido entre 4 y 12 carácteres")
    @Size(min = 2, max = 15, message = "El nombre de usuario debe estar comprendido entre 4 y 12 carácteres")
    private String nombreUsuario;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "La contraseña debe contener como minimo 8 caracteres que contengan, al menos un digito, una minúscula y una letra mayúscula")
    protected String password;
	boolean enabled;

    @ManyToOne()
    @JoinColumn(name = "autoridades", referencedColumnName = "autoridad")
    private Autoridades autoridades;
}
