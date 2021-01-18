package org.springframework.samples.petclinic.model;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.bytebuddy.asm.Advice.Local;

public class ActividadesValidadores {

    private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
    }
    
    @Test
    void noDeberiaValidarTematicaActividadVacio(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad(" ");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("tematicaActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarTematicaActividadNulo(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad(null);
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("tematicaActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío");
    }

    @Test
    void noDeberiaValidarTematicaActividadMas30Caracteres(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Esto es un campo de actividad exageradamente largo como para poder validarlo correctamente");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("tematicaActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarTematicaActividadMenos2Caracteres(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("E");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("tematicaActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("El nombre de la temática debe estar comprendido entre 2 y 30 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarDescripcionEnBlanco(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad(" ");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcionActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarDescripcionNulo(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad(null);
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcionActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarDescripcionMenos15Caracteres(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad("Esto es corto");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcionActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarDescripcionMas400Caracteres(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam id efficitur turpis, sit amet tincidunt dui. Sed vel aliquet ligula. Fusce a enim id elit auctor convallis et id ligula. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris dignissim placerat mattis. Aenean id est sit amet orci aliquam euismod ut at elit. Donec dapibus porta feugiat. Phasellus elementum libero vel nunc tincidunt, eu scelerisque odio efficitur. Ut consectetur, libero vel porttitor iaculis, risus libero mollis quam, vel iaculis lorem nulla et dui. Nam ultrices, leo rhoncus mollis lacinia, sem lacus condimentum neque, vitae fringilla ligula odio acorci. Quisque vehicula risus quis purus commodo, nec scelerisque orci consequat. Phasellus risus risus, sodales non feugiat nec, consectetur non nunc. Nulla facilisi. Nulla vel justo varius, pellentesque nisl eu, imperdiet orci. Morbi convallis nisl quis lectus euismod tristique. Sed tempus pellentesque turpis, sit amet porta arcu dapibus et. Nunc variusenim ac molestie hendrerit. Vivamus arcu tellus, tristique eu nulla in, pellentesque fringilla massa. Duis a augue non lorem viverra auctor nec sit amet nibh. Praesent pharetra aaugue non euismod. Mauris quis congue nunc, nec hendrerit elit. Nam ullamcorper lobortis justo, sit amet laoreet eros varius eget. Pellentesque semper dolor eget gravida ornare. Ut diam orci, vestibulum sit amet est non, semper convallis nunc. Aliquam pellentesque facilisis justo vitae ultrices. Mauris id risus consectetur, tincidunt eros vel, imperdiet velit. Pellentesque id urna dictum, dapibus ex vel, auctor arcu. In magna dui, convallis non vestibulum non, vestibulum vel lacus. Quisque at neque tempor, tincidunt risus quis, faucibus dui. Praesent pulvinar mi eu dui venenatis, in vehicula lorem tristique. Nulla vehicula massa quis augue fringilla, aliquam auctor ante aliquet. In facilisis, augue non laoreet vulputate, orci lorem molestie magna, ac efficitur lorem lorem vitae tellus. Integer imperdiet tortor eget posuere tristique. Aliquam ipsum sem, finibus sed aliquet quis, sagittis non ipsum. Fusce accumsan neque vel viverra blandit. Proin efficitur velit et ex accumsan, non mattis leo rutrum. Sed mi velit, dapibus quis ipsum ultricies, bibendum vestibulum ligula. Ut sed quam id felis aliquam viverra. Proin congue rutrum mauris, ac fermentum magna faucibus eget. Donec urna neque, fringilla in suscipit a, sagittis eu sem. In tempor varius diam, eget interdum tortor egestas in. Ut semper ex vitae consequat commodo. Pellentesque eget orci ac ex pretium elementum. Aenean orci massa, egestas vitae massa eget, consectetur vehicula turpis. Duis efficitur est.");
        actividad.setFechaFin(LocalDateTime.of(2031, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2031, 01, 12, 12, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(1);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    assertThat(violation.getPropertyPath().toString()).isEqualTo("descripcionActividad");
	  	  assertThat(violation.getMessage()).isEqualTo("La descripción de la temática debe estar comprendida entre 15 y 400 caracteres, además de no estar vacío");
    }
    @Test
    void noDeberiaValidarFechaInicioNulo(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2021, 01, 12, 20, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    //assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
	  	  assertThat(violation.getMessage()).isEqualTo("La fecha de inicio debe ser posterior a la actual y no puede ser nulo");
    }
    @Test
    void noDeberiaValidarFechaInicioPosteriorFechaActual(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2021, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.now().minusDays(1));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    //assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
	  	  assertThat(violation.getMessage()).isEqualTo("La fecha de inicio debe ser posterior a la actual y no puede ser nulo");
    }
    @Test
    void noDeberiaValidarFechaInicioPosteriorFechaFin(){
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Tematica Correcta");
        actividad.setDescripcionActividad("Esto es la descripcion de una actividad propuesta para un evento especifico");
        actividad.setFechaFin(LocalDateTime.of(2021, 01, 12, 20, 00));
        actividad.setFechaInicio(LocalDateTime.of(2020, 01, 12, 20, 00));
        
        Validator validator = createValidator();
		    Set<ConstraintViolation<Actividad>> constraintViolations = validator.validate(actividad);

		    assertThat(constraintViolations.size()).isEqualTo(2);
		    ConstraintViolation<Actividad> violation = constraintViolations.iterator().next();
		    //assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
	  	  assertThat(violation.getMessage()).isEqualTo("La fecha de inicio debe ser posterior a la actual y no puede ser nulo");
    }
}
