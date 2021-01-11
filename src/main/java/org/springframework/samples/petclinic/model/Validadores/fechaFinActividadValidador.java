package org.springframework.samples.petclinic.model.Validadores;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class fechaFinActividadValidador implements ConstraintValidator<FechaFinActividadRestriccion, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FechaFinActividadRestriccion fechaFin) {
        this.field = fechaFin.field();
        this.fieldMatch = fechaFin.fieldMatch();
    }

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(objeto).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(objeto).getPropertyValue(fieldMatch);
        LocalDate fechaInicio = LocalDate.parse(fieldValue.toString());
        LocalDate fechaFin = LocalDate.parse(fieldMatchValue.toString());
        return fechaInicio.isBefore(fechaFin);
    }

}
