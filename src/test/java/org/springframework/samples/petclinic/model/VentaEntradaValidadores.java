package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class VentaEntradaValidadores {
    
    private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
    
@Test
void noDeberiaValidarNombreEnBlanco(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        // ventaEntrada.setNombreTitular(" ");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombreTitular");
		assertThat(violation.getMessage()).isEqualTo("El nombre titular no puede estar vacio");
}

@Test
void noDeberiaValidarNumTarjetaEnBlanco(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("numTarjeta");
		assertThat(violation.getMessage()).isEqualTo("El numero de tarjeta no puede estar vacio");
}
@Test
void noDeberiaValidarNumTarjetaDeMas16Digitos(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("123456789132342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("numTarjeta");
		assertThat(violation.getMessage()).isEqualTo("El numero de tarjeta debe ser un numero de mas 16 digitos");
}
@Test
void noDeberiaValidarNumTarjetaDeMenos12Digitos(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("12345678912");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("numTarjeta");
		assertThat(violation.getMessage()).isEqualTo("El numero de tarjeta debe ser un numero de mas 16 digitos");
}
@Test
void noDeberiaValidarNumTarjetaConLetras(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("loltloolotlr");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(2);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("numTarjeta");
		assertThat(violation.getMessage()).isEqualTo("El numero de tarjeta debe ser un numero de mas 16 digitos");
}

@Test
void noDeberiaValidarFechaEnBlanco(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("1234567891232342");

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaCaducidad");
		assertThat(violation.getMessage()).isEqualTo("La fecha de inicio debe ser posterior a la actual");
}

@Test
void noDeberiaValidarFechaAnteriorHoy(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("111");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(1990, 03, 12));


		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaCaducidad");
		assertThat(violation.getMessage()).isEqualTo("La fecha de inicio debe ser posterior a la actual");
}
@Test
void noDeberiaValidarCVVEnBlanco(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv(" ");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(2);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("cvv");
		assertThat(violation.getMessage()).isEqualTo("El CVV no debe estar vacio y debe ser exactamente de 3 dígitos");
}
@Test
void noDeberiaValidarCVVDeMasDe3Digitos(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("3333");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("cvv");
		assertThat(violation.getMessage()).isEqualTo("El CVV no debe estar vacio y debe ser exactamente de 3 dígitos");
}
@Test
void noDeberiaValidarCVVDeMenosDe3Digitos(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("33");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("cvv");
		assertThat(violation.getMessage()).isEqualTo("El CVV no debe estar vacio y debe ser exactamente de 3 dígitos");
}
@Test
void noDeberiaValidarCVVConLetras(){
    // LocaleContextHolder.setLocale(Locale.ENGLISH);
		VentaEntrada ventaEntrada = new VentaEntrada();
        ventaEntrada.setNombreTitular("Persona de Prueba");
        ventaEntrada.setCvv("33l");
        ventaEntrada.setNumTarjeta("1234567891232342");
        ventaEntrada.setFechaCaducidad(LocalDate.of(2021, 03, 12));

		Validator validator = createValidator();
		Set<ConstraintViolation<VentaEntrada>> constraintViolations = validator.validate(ventaEntrada);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<VentaEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("cvv");
		assertThat(violation.getMessage()).isEqualTo("El CVV no debe estar vacio y debe ser exactamente de 3 dígitos");
}
}
