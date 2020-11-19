package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
@Table(name = "peticion")
public class Peticion extends NamedEntity{
    @OneToOne
    @JoinColumn(name = "FK_INVOICE", updatable = false, nullable = false)
    private Organizacion organizacion;

    @Column(name = "email")
    @NotEmpty
    protected String email;
    @Column(name = "nombreOrganizacion")
    @NotEmpty
    protected String nombreOrganizacion;
    @Column(name = "cif")
    @NotEmpty
    protected String cif;
    @Column(name = "info")
    @NotEmpty
    protected String info;

}
