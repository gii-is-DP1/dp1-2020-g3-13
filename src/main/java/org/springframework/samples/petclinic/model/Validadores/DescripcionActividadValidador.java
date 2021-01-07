package org.springframework.samples.petclinic.model.Validadores;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescripcionActividadValidador implements ConstraintValidator<DescripcionActividadRestriccion, String> {

    @Override
    public void initialize(DescripcionActividadRestriccion descripcionActividad){

    }

    @Override
    public boolean isValid(String descripcion, ConstraintValidatorContext context) {
        return descripcion != null && descripcion.length() > 15 && descripcion.length() < 400 && descripcion.trim().length() > 1;
    }
    
}
