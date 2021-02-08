package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



public class TipoEntradaValidadores {
	LugarRealizacion lugarRealizacion = new LugarRealizacion();
	AlquilerEspacio alquilerEspacio = new AlquilerEspacio();
	Actividad actividad = new Actividad();
	TipoEntrada tipoEntrada = new TipoEntrada();
	Evento evento = new Evento();

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@BeforeEach
	void setup() {

		lugarRealizacion.setAforo(2000);
		lugarRealizacion.setCaracteristicas("caracteristicas");
		lugarRealizacion.setDireccion("direccion del lugar");
		lugarRealizacion.setEmail("email@email.com");
		lugarRealizacion.setId(1);
		lugarRealizacion.setNombre_recinto("nombre_recinto");
		lugarRealizacion.setTelefono("555666777");
		lugarRealizacion.setUrlFoto("https://imagen.com");

		alquilerEspacio.setId(1);
		alquilerEspacio.setPrecioTotal(2000.00);
		alquilerEspacio.setLugarRealizacion(lugarRealizacion);
		alquilerEspacio.setFechaInicioReserva(LocalDateTime.now().plusDays(1));
		alquilerEspacio.setFechaFinReserva(alquilerEspacio.getFechaInicioReserva().plusDays(1));

		actividad.setDescripcionActividad("descripcion cualquiera");
		actividad.setAlquilerEspacio(alquilerEspacio);
		actividad.setFechaInicio(LocalDateTime.now().plusDays(1).plusHours(1));
		actividad.setFechaFin(actividad.getFechaInicio().plusDays(1));
		actividad.setTematicaActividad("tematicaActividad");
		// actividad.setTiposEntrada(new ArrayList<TipoEntrada>());
	}

	@Test
	void noDeberiaValidarFechaInicioAnteriorActual() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.now().minusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(4));
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
	void noDeberiaValidarFechaInicioAnteriorAFin() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.now().minusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().minusDays(1));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(2);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");

		assertThat(violation.getMessage()).isIn("Las actividades elegidas deben ser acordes las fechas de la entrada, por favor, seleccione las actividades que se le muestran acorde a su fecha", "La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");
			}


	@Test
	void noDeberiaValidarFechaInicioNocturnaSiNombreDiurna() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 17, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 20, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 16, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 22, 00, 00));
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
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 12, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 7, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 13, 00, 00));
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
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 12, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 7, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 13, 00, 00));
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
	void noDeberiaValidarFechaUnDiaSiNombreVariosDias() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 12, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 7, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 13, 00, 00));
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
	void noDeberiaValidarFechaInicioYFinUnSoloDiaSiNombreVariosDias() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
		tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(20));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_UN_DIA);
		tipoEntrada.setNumEntradas(10);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

	@Test
	void noDeberiaValidarFechaFinNocturnaSiNombreDiurna() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 12, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 20, 00, 00));
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
	void noDeberiaValidarSiFechaActividadFueraFechaTipoEntrada() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 02, 8, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 02, 12, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 8, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 20, 00, 00));
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
				"Las actividades elegidas deben ser acordes las fechas de la entrada, por favor, seleccione las actividades que se le muestran acorde a su fecha");

	}

	@Test
	void noDeberiaValidarSiAforoMenorNumeroEntradas() {
		// Construimos el objeto tipoEntrada
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividad.setFechaInicio(LocalDateTime.of(2025, 01, 01, 17, 00, 00));
		actividad.setFechaFin(LocalDateTime.of(2025, 01, 01, 20, 00, 00));
		actividades.add(actividad);
		tipoEntrada.setActividades(actividades);
		tipoEntrada.setEvento(evento);
		tipoEntrada.setId(1);
		tipoEntrada.setFechaInicio(LocalDateTime.of(2025, 01, 01, 16, 00, 00));
		tipoEntrada.setFechaFin(LocalDateTime.of(2025, 01, 01, 22, 00, 00));
		tipoEntrada.setNombre(NombreTiposEntrada.PASE_UN_DIA);
		tipoEntrada.getActividades().get(0).getAlquilerEspacio().getLugarRealizacion().setAforo(200);
		tipoEntrada.setNumEntradas(4000000);
		tipoEntrada.setPrecio(1.0);
		// Validamos
		Validator validator = createValidator();
		Set<ConstraintViolation<TipoEntrada>> constraintViolations = validator.validate(tipoEntrada);
		assertThat(constraintViolations.size()).isEqualTo(0);
		ConstraintViolation<TipoEntrada> violation = constraintViolations.iterator().next();
		// assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo(
				"La fecha de inicio debe posterior a la actual, además debe corresponder el nombre de la entrada (En cuestión horaria) con la elección del inicio de la fecha del evento");

	}

}

 
