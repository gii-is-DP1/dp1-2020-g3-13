package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ClienteValidadores {
    
    
    private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
    }

    @Test
    void noDeberiaValidarTelefonoVacio(){
        Cliente cliente = new Cliente();
        cliente.setNombre("José");
        cliente.setApellidos("Pérez García");
        cliente.setTelefono(" ");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

		    assertThat(constraintViolations.size()).isEqualTo(3);
		    ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
	  	    assertThat(violation.getMessage()).isEqualTo("El telefono no puede estar vacio, y debe ser exactamente de 9 caracteres");
    }

    @Test
    void noDeberiaValidarTelefonoDeMenosDe9Caracteres(){
        Cliente cliente = new Cliente();
        cliente.setTelefono("9879");
        cliente.setNombre("José");
        cliente.setApellidos("Pérez García");
        
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
	  	    assertThat(violation.getMessage()).isEqualTo("El telefono no puede estar vacio, y debe ser exactamente de 9 caracteres");
    }

    @Test
    void noDeberiaValidarTelefonoDeMasDe9Caracteres(){
        Cliente cliente = new Cliente();
        cliente.setTelefono("98749790989879");
        cliente.setNombre("José");
        cliente.setApellidos("Pérez García");
        
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
	  	    assertThat(violation.getMessage()).isEqualTo("El telefono no puede estar vacio, y debe ser exactamente de 9 caracteres");
    }

}
