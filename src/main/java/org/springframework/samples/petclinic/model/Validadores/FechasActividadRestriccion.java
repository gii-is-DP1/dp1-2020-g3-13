package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FechasActividadesValidador.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechasActividadRestriccion {
   
    String message() default "La fecha de inicio debe ser posterior a la actual y no puede ser nulo";
    String fechaInicio();
    String fechaFin();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
