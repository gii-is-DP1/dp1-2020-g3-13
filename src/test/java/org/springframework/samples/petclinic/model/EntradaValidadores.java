package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class EntradaValidadores {

       private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
    }

    @Test
    void deberiaValidarDNISinLetra(){
        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("John Doe");
        entrada.setDni("77934193");

        Validator validator = createValidator();
	    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);
        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
        assertThat(violation.getMessage()).isEqualTo("El dni debe tener 8 dígitos y una letra");
    
    
    }

    @Test
    void deberiaValidarDNIConLetraYMenosNumeros(){
        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("John Doe");
        entrada.setDni("7793419G");

        Validator validator = createValidator();
	    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);
        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
        assertThat(violation.getMessage()).isEqualTo("El dni debe tener 8 dígitos y una letra");
    
    
    }

    @Test
    void deberiaValidarDNILetraIncorrecta(){
        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("John Doe");
        entrada.setDni("77934193O");

        Validator validator = createValidator();
	    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);
        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
        assertThat(violation.getMessage()).isEqualTo("El dni debe tener 8 dígitos y una letra");
    
    
    }


}
