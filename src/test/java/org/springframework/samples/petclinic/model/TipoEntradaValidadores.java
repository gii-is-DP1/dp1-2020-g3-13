package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class TipoEntradaValidadores {

	@Autowired
	private EventoService eventoService;

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void noDeberiaValidarPrecioMenorQueCero() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = InicializadorObjetosTest.tipoEntradaParaTest();
		tipoEntrada.setPrecio(-1.0);
		 
		//Construimos el tipo Evento
		Evento evento = InicializadorObjetosTest.eventoParaTest();
		evento.setFechaInicio(LocalDate.now().minusDays(2));
		evento.setFechaFin(LocalDate.now().plusDays(2));
		tipoEntrada.setEvento(evento);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("precio");
		assertThat(violation.getMessage()).isEqualTo("El precio debe ser superior o igual a 0");

	}

	@Test
	void noDeberiaValidarNumEntradasMenorQueUno() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
		tipoEntrada.setNumEntradas(0);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("numEntradas");
		assertThat(violation.getMessage()).isEqualTo("Debe tener al menos 1 entrada disponible para el evento");

	}

	@Test
	void noDeberiaValidarFechaInicioAnteriorActual() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.now().minusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(2));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaFinAnteriorAInicio() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(2));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().minusDays(1));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaInicioNocturnaSiNombreDiurna() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.of(2050, 01, 01, 17, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2050, 01, 01, 18, 00, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.DIURNA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaInicioDiurnaSiNombreNocturna() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.of(2050, 01, 01, 11, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2050, 01, 01, 14, 00, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.NOCTURNA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaInicioYFinVariosDiasSiNombreUnSoloDia() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_UN_DIA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaInicioYFinUnSoloDiaSiNombreVariosDias() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.of(2050, 1, 1, 19, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2050, 1, 1, 19, 01, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaFinNocturnaSiNombreDiurna() {
		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.of(2050, 01, 01, 12, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2050, 01, 01, 18, 00, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.DIURNA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);

		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}
/*
	@Test
	void noDeberiaValidarFechaInicioPosteriorFechaInicioEvento() {
		//Construimos el Evento
		Evento evento = new Evento();
		evento.setFechaInicio(LocalDate.of(2050, 01, 01));
		eventoService.findEventoById(1).getFechaInicio();

		// Construimos el objeto tipoEntrada
		TipoEntrada tipoEntrada = new TipoEntrada();
		tipoEntrada.setFechaInicio(LocalDateTime.of(2050, 01, 01, 12, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2050, 01, 01, 18, 00, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.DIURNA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);

		tipoEntrada.setEvento(evento);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}
	*/
}
