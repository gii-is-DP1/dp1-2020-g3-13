package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;


import org.springframework.samples.petclinic.model.Consulta;

import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ExponenteServiceTest {
        @Autowired
        private ExponenteService exponenteService;
        @Autowired
        private EventoService eventoService;
        @Autowired
        private ActividadService actividadService;

        @Test
        public void deberiaEncontrarExponente(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("Test 1");
            exponenteService.guardarExponente(exponente);
            Exponente pruebaMetodo = exponenteService.buscaExponente(exponente);
            assertEquals(exponente, pruebaMetodo);
        }
        @Test
        public void deberiaAnadirExponenteNuevoAActividad(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("Test 2");
            //exponente.setActividades(new ArrayList<Actividad>());
            Actividad actividadExpo = new Actividad();
            int cantidad = eventoService.eventosCount();
            Evento evento = new Evento();
            Evento eventoCreado = eventoService.findAll().iterator().next();
            evento.setDescripcion("descripcion");
            evento.setFechaInicio(LocalDate.of(2022, 03, 02));
            evento.setFechaFin(LocalDate.of(2022, 03, 04));
            evento.setId(cantidad);
            evento.setNombreEvento("nombreEvento");
            evento.setOrganizacion(eventoCreado.getOrganizacion());
            evento.setTipoEvento(TipoEvento.CULTURALES_DE_OCIO);
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);
            exponenteService.anadirExponente(actividadExpo, exponente);
            assertTrue(actividadExpo.getExponentes().contains(exponente));
    
        }

        @Test
        public void deberiaAnadirExponenteExistenteAActividad(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("Test 3");
            //exponente.setActividades(new ArrayList<Actividad>());
            Actividad actividadExpo = new Actividad();
            int cantidad = eventoService.eventosCount();
            Evento evento = new Evento();
            Evento eventoCreado = eventoService.findAll().iterator().next();
            evento.setDescripcion("descripcion");
            evento.setFechaInicio(LocalDate.of(2022, 03, 02));
            evento.setFechaFin(LocalDate.of(2022, 03, 04));
            evento.setId(cantidad);
            evento.setNombreEvento("nombreEvento");
            evento.setOrganizacion(eventoCreado.getOrganizacion());
            evento.setTipoEvento(TipoEvento.CULTURALES_DE_OCIO);
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            Exponente exponenteNuevo = new Exponente();
            exponenteNuevo.setNombreExponente("Exponente");
            exponenteNuevo.setApellidosExponente("De Prueba");
            exponenteNuevo.setAlias("test 3");
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);
            exponenteService.guardarExponente(exponenteNuevo);
            exponenteService.anadirExponente(actividadExpo, exponenteNuevo);
            assertFalse(actividadExpo.getExponentes().contains(exponenteNuevo));
        }

        @Test
        public void deberiaEncontrarExponentesActividad(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("Test 4");
            Actividad actividadExpo = new Actividad();
            int cantidad = eventoService.eventosCount();
            Evento evento = new Evento();
            Evento eventoCreado = eventoService.findAll().iterator().next();
            evento.setDescripcion("descripcion");
            evento.setFechaInicio(LocalDate.of(2022, 03, 02));
            evento.setFechaFin(LocalDate.of(2022, 03, 04));
            evento.setId(cantidad);
            evento.setNombreEvento("nombreEvento");
            evento.setOrganizacion(eventoCreado.getOrganizacion());
            evento.setTipoEvento(TipoEvento.CULTURALES_DE_OCIO);
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            List<Exponente> exponentes = new ArrayList<Exponente>();
            exponentes.add(exponente);
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            actividadExpo.setExponentes(exponentes);
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);           
            List<Exponente> expoActividad = actividadExpo.getExponentes();
            assertTrue(expoActividad.contains(exponente));
        }

        @Test
        public void deberiaEliminarExponentesActividad(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("Test 5");
            Actividad actividadExpo = new Actividad();
            int cantidad = eventoService.eventosCount();
            Evento evento = new Evento();
            Evento eventoCreado = eventoService.findAll().iterator().next();
            evento.setDescripcion("descripcion");
            evento.setFechaInicio(LocalDate.of(2022, 03, 02));
            evento.setFechaFin(LocalDate.of(2022, 03, 04));
            evento.setId(cantidad);
            evento.setNombreEvento("nombreEvento");
            evento.setOrganizacion(eventoCreado.getOrganizacion());
            evento.setTipoEvento(TipoEvento.CULTURALES_DE_OCIO);
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            List<Exponente> exponentes = new ArrayList<Exponente>();
            exponentes.add(exponente);
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            actividadExpo.setExponentes(exponentes);
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);           
            exponenteService.eliminaExponente(exponente, actividadExpo);
            assertFalse(actividadExpo.getExponentes().contains(exponente));
        }
}
