package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ValidadorCIFValidador.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidadorCIFRestriccion {
    String message() default "El CIF ingresado no es correcto";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

     
}

