package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TipoEntradaServiceTest {

    @Autowired
    private TipoEntradaService tipoEntradaService;
    @Autowired
    private EventoService eventoService;

    // Comprueba los datos iniciales, si ya hay datos en el sql dará error
    @Test
    public void contarDatosIniciales() {
        int count = tipoEntradaService.tipoEntradaCount();
        assertEquals(count, 0);

    }

    // Comprueba que el metodo de contar entradas no tiene errores
    @Test
    public void deberiaContarTiposEntrada() {
        int countServ = tipoEntradaService.tipoEntradaCount();
        int countRepo = tipoEntradaService.tipoEntradaCount();
        assertEquals(countServ, countRepo);
    }

    // Comprueba que no hay errores al añadir un tipo de entrada a un evento
    @Test
    public void deberiaAñadirTipoEntrada() {
        Evento evento = eventoService.findEventoById(1);
        int numeroTipoEntradasAntes = evento.getTipoEntradas().size();
        TipoEntrada tipoEntrada = new TipoEntrada();
        tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
        tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
        tipoEntrada.setId(Integer.MAX_VALUE);
        tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
        tipoEntrada.setPrecio(12.0);
        tipoEntrada.setEvento(evento);
        evento.getTipoEntradas().add(tipoEntrada);
        int numeroEntradasDespues = evento.getTipoEntradas().size();
        assertEquals(numeroTipoEntradasAntes + 1, numeroEntradasDespues);

    }

        // Comprueba que no hay errores al añadir un tipo de entrada a un evento
        @Test
        public void deberiaAñadir90porCiento() {
            int numeroEntradasIntroducidas = 50;
            int numeroEntradasDespues = 45;
            Evento evento = eventoService.findEventoById(1);
            TipoEntrada tipoEntrada = new TipoEntrada();
            tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
            tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
            tipoEntrada.setId(tipoEntradaService.tipoEntradaCount());
            tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
            tipoEntrada.setPrecio(12.0);
            tipoEntrada.setEvento(evento);
            tipoEntrada.setNumEntradas(numeroEntradasIntroducidas);
            evento.getTipoEntradas().add(tipoEntrada);
            tipoEntradaService.soloVentaAl90PorCiento(tipoEntrada);
            assertEquals(numeroEntradasDespues, tipoEntrada.getNumEntradas());
    
        }

}
