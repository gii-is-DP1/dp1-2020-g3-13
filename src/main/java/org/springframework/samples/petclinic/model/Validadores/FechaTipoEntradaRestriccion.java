
package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = FechaTipoEntradaValidador.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaTipoEntradaRestriccion {
    String message() default "La fecha de inicio debe ser posterior a la actual además de corresponderse con el tipo de entrada";
    String fechaInicio();
    String fechaFin();
    String nombreEntrada();
    //String evento_id();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
	String evento_id();

     
}
