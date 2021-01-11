package org.springframework.samples.petclinic.model.Validadores;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class FechaInicioTimeValidador  implements ConstraintValidator<FechaInicioTimeRestriccion, LocalDateTime> {
    

    @Override
    public void initialize(FechaInicioTimeRestriccion fechaInicio) {
    }

    @Override
    public boolean isValid(LocalDateTime fechaInicio, ConstraintValidatorContext context) {
        return fechaInicio != null && fechaInicio.isAfter(LocalDateTime.now()) ;
    }
}
