package org.springframework.samples.petclinic.model.Validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorCIFValidador implements ConstraintValidator<ValidadorCIFRestriccion, String> {

    @Override
    public void initialize(ValidadorCIFRestriccion respuesta) {
    }

    @Override
    public boolean isValid(String cif, ConstraintValidatorContext context) {

        String matches = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
        if (cif.length() > 0) {

            if (matches.contains(Character.toString(cif.charAt(0)))) {
                return cif.length() == 9 || cif.length() == 8;
            } else {
                return false;

            }
        }
        return false;
    }
}