package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;



@Constraint(validatedBy = ActividadParaEventoValidador.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActividadParaEventoRestriccion {
    String message() default "Las fechas de actividades elegidas deben ser acordes a las de evento, por favor, seleccione las actividades que se le muestran acorde a su fecha";
    String fechaInicio();
    String fechaFin();
    String evento();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}