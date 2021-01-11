package org.springframework.samples.petclinic.model.Validadores;
import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FechaInicioValidador implements ConstraintValidator<FechaInicioRestriccion, LocalDate> {

    @Override
    public void initialize(FechaInicioRestriccion descripcionActividad){

    }

    @Override
    public boolean isValid(LocalDate fechaInicio, ConstraintValidatorContext context) {
        return fechaInicio != null && fechaInicio.isAfter(LocalDate.now());
    }
    
}