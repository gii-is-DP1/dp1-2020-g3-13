package org.springframework.samples.petclinic.model.Validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ActividadesParaTipoEntradaValidador.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActividadesParaTipoEntradaRestriccion {
    String message() default "Las actividades elegidas deben ser acordes las fechas de la entrada, por favor, seleccione las actividades que se le muestran acorde a su fecha";
    String fechaInicio();
    String fechaFin();
    String actividades();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
