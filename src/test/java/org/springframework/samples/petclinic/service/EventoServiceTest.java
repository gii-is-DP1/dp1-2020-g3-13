package org.springframework.samples.petclinic.service;


import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;

import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private EventoRepository eventoRepo;

    @Test
    public void testCountWithInitialData() {
        int count = eventoService.eventosCount();
        assertEquals(count, 1);
    }

    @Test
    //TODO
    public void deberiaInsertarEvento(){
        int cantidad = eventoService.eventosCount();
        Evento evento = new Evento();
        Evento eventoCreado = eventoService.findAll().iterator().next();
        evento.setDescripcion("descripcion");
        evento.setFechaInicio(LocalDate.now().plusDays(10));
        evento.setFechaFin(LocalDate.now().plusDays(20));
        evento.setId(cantidad);
        evento.setNombreEvento("nombreEvento");
        evento.setOrganizacion(eventoCreado.getOrganizacion());
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        eventoService.save(evento);
        assertEquals(eventoService.eventosCount(), cantidad+1);
    }

    @Test
    public void deberiaEliminarEventos(){
        Evento evento = eventoService.findAll().iterator().next();
        int cantidadAntes = eventoService.eventosCount();
        eventoService.delete(evento);
        int cantidadDespues = eventoService.eventosCount();
        assertEquals(cantidadAntes-1, cantidadDespues);
    }

    @Test
    public void deberiaEditarEventos(){
        int cantidad = eventoService.eventosCount();
        Evento evento = new Evento();
        Evento eventoCreado = eventoService.findAll().iterator().next();
        evento.setDescripcion("Esto es una descripcion bastante apropiada para un evento de prueba");
        evento.setFechaInicio(LocalDate.now().plusDays(10));
        evento.setFechaFin(LocalDate.now().plusDays(20));
        evento.setId(cantidad);
        evento.setNombreEvento("nombreEvento");
        evento.setOrganizacion(eventoCreado.getOrganizacion());
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        eventoService.save(evento);
        Evento eventoMod = evento;
        eventoMod.setId(cantidad+1);
        eventoMod.setNombreEvento("nombreEventoMod");
        eventoService.modificarEvento(eventoMod, evento);
        assertEquals(evento.getId(), eventoMod.getId());
    }
   
}
