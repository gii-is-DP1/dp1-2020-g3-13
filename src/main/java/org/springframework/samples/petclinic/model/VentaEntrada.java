package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Validadores.FechaInicioRestriccion;
//import org.springframework.samples.petclinic.model.Validadores.NumeroTarjetaRestriccion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ventaEntrada")

public class VentaEntrada extends BaseEntity {

    @NotBlank(message = "El numero de tarjeta no puede estar vacio")
    @Digits(fraction = 0, integer = 16, message = "El numero de tarjeta debe ser un numero de menos de 16 digitos")
    @Length(min = 16, message ="El numero de tarjeta debe ser un numero de mas 16 digitos" )
    private String numTarjeta;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @FechaInicioRestriccion
    private LocalDate fechaCaducidad;

    @NotBlank(message = "El nombre titular no puede estar vacio")
    @Size(min=8, max=200, message="El titular debe estar comprendido entre 8 y 200 caracteres")
    private String nombreTitular;

    
    @NotEmpty(message = "El CVV no debe estar vacio y debe ser exactamente de 3 dígitos")
    @Digits(fraction = 0, integer = 3, message = "El CVV no debe estar vacio y debe ser exactamente de 3 dígitos")
    @Length(min = 3, message ="El CVV no debe estar vacio y debe ser exactamente de 3 dígitos")
    private String cvv;   
}
