package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.model.VentaEntrada;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void testCountWithInitialData() {
        int count = eventoService.eventosCount();
        assertEquals(count, 1);
    }

    @Test
    public void deberiaInsertarEvento(){
        int cantidad = (int) eventoRepository.count();
        Evento evento = new Evento();
        Evento eventoCreado = eventoService.findAll().iterator().next();
        evento.setActividades(new ArrayList<Actividad>());
        evento.setCategoria("categoria");
        evento.setConsultas(new ArrayList<Consulta>());
        evento.setDescripcion("descripcion");
        evento.setFechaFin(LocalDate.of(2020, 2, 02));
        evento.setFechaInicio(LocalDate.of(2020, 2, 03));
        evento.setId(3);
        evento.setMedidasSanitarias("medidasSanitarias");
        evento.setNombreEvento("nombreEvento");
        evento.setOrganizacion(eventoCreado.getOrganizacion());
        evento.setTipoEntradas(new ArrayList<TipoEntrada>());
        evento.setTiposEntradas(new ArrayList<TipoEntrada>());
        evento.setVentaEntrada(new ArrayList<VentaEntrada>());
        evento.setTipoEvento("tipoEvento");
        this.eventoService.save(evento);
        assertEquals((int) this.eventoRepository.count(),cantidad+1);
    }

    @Test
    public void deberiaEliminarEventos(){
        Evento evento = this.eventoRepository.findAll().iterator().next();
        int cantidad = (int) eventoRepository.count();
        this.eventoService.delete(evento);
        assertEquals((int) eventoRepository.count(),cantidad-1);
    }
}
