package org.springframework.samples.petclinic.model.Validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.security.core.context.SecurityContextHolder;

public class RespuestaLimiteOrganizacionValidador
        implements ConstraintValidator<RespuestaLimiteOrganizacionRestriccion, Object> {

    @Autowired
    private OrganizacionService organizacionService;

    @Override
    public void initialize(RespuestaLimiteOrganizacionRestriccion respuesta) {
    }

    @Override
    public boolean isValid(Object respuesta, ConstraintValidatorContext context) {

        Boolean org = organizacionService
                .hayOrganizacionConEseUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        Boolean res = false;
        if (org == false) {

            res = true;
        } else {
            System.out.println("hola");
            res = respuesta.toString().length() <= 500 && respuesta.toString().length() > 50;
        }
        return res;
    }

}
