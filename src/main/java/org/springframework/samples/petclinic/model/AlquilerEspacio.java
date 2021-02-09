package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alquilerEspacio")
public class AlquilerEspacio extends BaseEntity{

    @Column(name = "precioTotal")
    // @NotEmpty
    private Double precioTotal;

    @Column(name = "fechaInicioReserva")
    // @NotEmpty
    private LocalDateTime fechaInicioReserva;

    @Column(name = "fechaFinReserva")
    // @NotEmpty
    private LocalDateTime fechaFinReserva;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_lugar_realizacion", referencedColumnName = "id")
    private LugarRealizacion lugarRealizacion;

}