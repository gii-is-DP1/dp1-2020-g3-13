package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DescripcionActividadValidador.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DescripcionActividadRestriccion {
    String message() default "El nombre de la temática debe estar comprendido entre 15 y 400 caracteres, además de no estar vacío";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    
}
