package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class ConsultaValidadores {


    

    private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
    }

    @Test
    void noDeberiaValidarAsuntoVacio(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto(" ");
        consulta.setDescripcion("Aquí se detalla la consulta en sí");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("asunto");
	  	    assertThat(violation.getMessage()).isEqualTo("El asunto no puede estar vacio");
    }

    @Test
    void noDeberiaValidarAsuntoNulo(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto(null);
        consulta.setDescripcion("Aquí se detalla la consulta en sí");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("asunto");
	  	    assertThat(violation.getMessage()).isEqualTo("El asunto no puede estar vacio");
    }


    @Test
    void noDeberiaValidarAsuntoConMasde30Caracteres(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto("Esto es un asunto con una longitud excesivamente larga");
        consulta.setDescripcion("Aquí se detalla la consulta en sí");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("asunto");
            assertThat(violation.getMessage()).isEqualTo("El asunto no debe superar los 30 caracteres");
    }


    @Test
    void noDeberiaValidarDescripcionVacia(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto("Error inicio de sesion");
        consulta.setDescripcion(null);
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcion");
	  	    assertThat(violation.getMessage()).isEqualTo("La descripcion no puede estar vacia");
    }

    @Test
    void noDeberiaValidarDescripcionConMenosDe15Caracteres(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto("Error inicio");
        consulta.setDescripcion("No funciona");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcion");
	  	    assertThat(violation.getMessage()).isEqualTo("La descripción debe estar comprendida entre 15 y 400 caracteres");
    }

    @Test
    void noDeberiaValidarDescripcionConMasDe250Caracteres(){
        Consulta consulta  = new Consulta();
        consulta.setAsunto("Error inicio de sesion");
        consulta.setDescripcion("Con diez cañones por banda Viento en popa a toda vela No corta el mar si no vuela Un velero bergantín Bajel pirata que llaman Por su bravura el temido En todo el mar conocido Del uno al otro confín La luna en el mar riela Y en la lona gime el viento Y alza en blando movimiento Olas de plata y azul Y ve el capitán pirata Cantando alegre en la popa Asia a un lado, al otro Europa Y allá a su frente Estambul Navega velero mío Sin temor que ni enemigo navío Ni tormenta ni bonanza Tu rumbo a torcer alcanza Ni a sujetar tu valor Veinte presas hemos hecho A despecho del inglés Y han rendido sus pendones Cien naciones a mis pies Que es mi barco mi tesoro Que es mi Dios mi libertad Mi ley la fuerza y el viento Mi única patria la mar Allá muevan feroz guerras Ciegos reyes, por un palmo más de tierra Que yo tengo aquí por mío Cuanto abarca el mar bravío A quien nadie impuso leyes Y no hay playa sea cualquiera Ni bandera de esplendor Que no sienta mi derecho Y de pecho a mi valor Que es mi barco mi tesoro Que es mi Dios mi libertad Mi ley la fuerza y el viento Mi única patria la mar ");
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Consulta>> constraintViolations = validator.validate(consulta);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Consulta> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcion");
	  	    assertThat(violation.getMessage()).isEqualTo("La descripción debe estar comprendida entre 15 y 400 caracteres");
    }



}

