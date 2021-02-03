package org.springframework.samples.petclinic.model.Validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RespuestaLimiteOrganizacionValidador implements ConstraintValidator<RespuestaLimiteOrganizacionRestriccion, String> {

    @Override
    public void initialize(RespuestaLimiteOrganizacionRestriccion respuesta) {
    }

    @Override
    public boolean isValid(String respuesta, ConstraintValidatorContext context) {
        if (respuesta == null) {
            System.out.println("soy cliente");
            return true;
        }else{
            System.out.println("entro aqui");
            int tamanio = respuesta.toString().length();
            System.out.println(tamanio);
            return (tamanio <= 500 && tamanio > 15);
        }
    }
}
