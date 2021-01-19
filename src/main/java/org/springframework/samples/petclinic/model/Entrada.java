package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrada")
public class Entrada extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_tipoEntrada", referencedColumnName = "id")
    private TipoEntrada tipoEntrada;

    @Column(name = "nombreAsistente")
    @NotEmpty
    private String nombreAsistente;

    @Column(name = "dni")
    @NotEmpty
    private String dni;
}
