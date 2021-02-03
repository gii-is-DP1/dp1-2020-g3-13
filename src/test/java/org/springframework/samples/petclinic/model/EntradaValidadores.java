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
    void noDeberiavalidarDNIDeMasDe9Caracteres(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("Pepe");
        entrada.setDni("4353455334545");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
	  	    assertThat(violation.getMessage()).isEqualTo("El DNI no puede estar vacio, y debe ser exactamente de 9 caracteres");
        

    }

    @Test
    void noDeberiavalidarDNIDeMenosDe9Caracteres(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("Pepe");
        entrada.setDni("123");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
	  	    assertThat(violation.getMessage()).isEqualTo("El DNI no puede estar vacio, y debe ser exactamente de 9 caracteres");
        

    }


    @Test
    void noDeberiavalidarDNIVacio(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("Pepe");
        entrada.setDni("");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
	  	    assertThat(violation.getMessage()).isEqualTo("El DNI no puede estar vacio, y debe ser exactamente de 9 caracteres");
        

    }

    @Test
    void noDeberiavalidarNombreAsistenteVacio(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("");
        entrada.setDni("49789456M");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("nombreAsistente");
	  	    assertThat(violation.getMessage()).isEqualTo("El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres");
        

    }

    @Test
    void noDeberiavalidarNombreAsistenteDeMasDe35Caracteres(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("Estafraseestáformadaporuntotalde44caracteres");
        entrada.setDni("49789456M");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("nombreAsistente");
	  	    assertThat(violation.getMessage()).isEqualTo("El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres");
        

    }

    @Test
    void noDeberiavalidarNombreAsistenteDeMenosDe3Caracteres(){

        Entrada entrada = new Entrada();
        entrada.setNombreAsistente("Bo");
        entrada.setDni("49789456M");

        Validator validator = createValidator();
		    Set<ConstraintViolation<Entrada>> constraintViolations = validator.validate(entrada);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Entrada> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("nombreAsistente");
	  	    assertThat(violation.getMessage()).isEqualTo("El nombre del asistente no puede estar vacío y debe estar comprendido entre 2 y 35 caracteres");
        

    }

}
