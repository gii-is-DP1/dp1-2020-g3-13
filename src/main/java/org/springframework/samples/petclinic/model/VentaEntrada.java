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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.FechaFinActividadRestriccion;
import org.springframework.samples.petclinic.model.Validadores.FechaInicioRestriccion;
import org.springframework.samples.petclinic.model.Validadores.NumeroTarjetaRestriccion;

import lombok.Data;

@Data
@Entity
@Table(name = "ventaEntrada")

public class VentaEntrada extends BaseEntity {
    
    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name="id_cliente",referencedColumnName = "id")
    // private Cliente cliente;

    // @OneToOne(cascade = CascadeType.ALL)
    // private LineaFactura lf;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "evento_id", referencedColumnName = "id")
    // private Evento evento;
    @NotBlank(message = "El numero de tarjeta no puede estar vacio")
    @Digits(fraction = 0, integer = 12, message = "El numero de tarjeta debe ser un numero de menos de 12 digitos")
    @Length(min = 12, message ="El numero de tarjeta debe ser un numero de mas 12 digitos" )
    private String numTarjeta;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @FechaInicioRestriccion
    private LocalDate fechaCaducidad;

    @NotBlank(message = "El nombre titular no puede estar vacio")
    @Size(min=8, max=200, message="El titular debe estar comprendido entre 8 y 200 caracteres")
    private String nombreTitular;

    
    @NotEmpty(message = "El CVV no puede estar vacio")
    @Digits(fraction = 0, integer = 3, message = "El CVV no debe tener mas de 3 digitos")
    @Length(min = 3, message ="El CVV no debe tener menos de 3 digitos")
    private String cvv;   
}
