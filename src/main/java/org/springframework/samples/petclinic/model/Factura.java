package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura extends BaseEntity{

    @Column(name="precioTotal")
    @NotNull
    private Double precioTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    @Column(name = "fechaFactura")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFactura;

}
