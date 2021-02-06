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
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {
    @Autowired
    private EventoService eventoService;

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
        evento.setActividades(new ArrayList<Actividad>());
        evento.setCategoria("categoria");
        evento.setConsultas(new ArrayList<Consulta>());
        evento.setDescripcion("descripcion");
        evento.setFechaInicio(LocalDate.now().plusDays(10));
        evento.setFechaFin(LocalDate.now().plusDays(20));
        evento.setId(cantidad);
        evento.setMedidasSanitarias("medidasSanitarias");
        evento.setNombreEvento("nombreEvento");
        evento.setOrganizacion(eventoCreado.getOrganizacion());
        evento.setTipoEntradas(new ArrayList<TipoEntrada>());
        evento.setTipoEntradas(new ArrayList<TipoEntrada>());
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
        Evento evento = eventoService.findAll().iterator().next();
        evento.setCategoria("evento de prueba");
        assertEquals("evento de prueba", evento.getCategoria());
    }
   
}
