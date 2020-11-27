package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "ventaEntrada")
public class VentaEntrada extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    private LineaFactura lf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @NotNull
    private Integer numTarjeta;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCaducidad;

    @NotEmpty
    private String nombreTitular;

    @NotNull
    private Integer cvv;

    @Column(name = "nombreAsistente")
    @NotEmpty
    protected String nombreAsistente;

    @Column(name = "dni")
    @NotEmpty
    protected String dni;
    
    
}
