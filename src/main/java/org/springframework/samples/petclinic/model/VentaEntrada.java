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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "ventaEntrada", uniqueConstraints={
    @UniqueConstraint(columnNames = {"id_evento", "nombreAsistente"})
})
public class VentaEntrada extends BaseEntity {
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ventaEntrada")
    @JoinColumn(name = "id_linea_factura", referencedColumnName = "id")
    private LineaFactura lf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @NotNull
    private Integer numTarjeta;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    //@NotNull
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
