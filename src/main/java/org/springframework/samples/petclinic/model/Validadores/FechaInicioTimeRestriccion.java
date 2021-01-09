package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FechaInicioTimeValidador.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaInicioTimeRestriccion {
    String message() default "La fecha de inicio debe ser posterior a la actual y no puede ser nulo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

     
}