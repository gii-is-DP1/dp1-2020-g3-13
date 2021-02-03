package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ActividadAforoYNumeroEntradasValidacion.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActividadAforoYNumeroEntradasRestriccion {
    String message() default "El numero de entradas a vender no puede ser mayor que el aforo restante por ocupar";
    String numEntradas();
    String actividades();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
