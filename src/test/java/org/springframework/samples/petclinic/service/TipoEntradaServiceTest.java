package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TipoEntradaServiceTest {

    @Autowired
    private TipoEntradaService tipoEntradaService;
    @Autowired
    private TipoEntradaRepository tipoEntradaRepo;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private EntradaService entradaService;

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
        int countRepo = (int) tipoEntradaRepo.count();
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
    //Comprueba que el numero de entradas con ese tipo de entradas que devuelve el metodo sea el correcto
    @Test
    public void deberiaSacarTodosTiposEntrada(){
        TipoEntrada tipoEntrada = new TipoEntrada();
        tipoEntrada.setFechaInicio(LocalDateTime.now().plusDays(1));
        tipoEntrada.setFechaFin(tipoEntrada.getFechaInicio().plusDays(1));
        tipoEntrada.setId(tipoEntradaService.tipoEntradaCount()+1);
        tipoEntrada.setNombre(NombreTiposEntrada.PASE_VARIOS_DIAS);
        tipoEntrada.setPrecio(12.0);
        tipoEntradaService.guardar(tipoEntrada);
        //Numero de entradas antes
        int numeroDeEntradasAntes = tipoEntradaService.EncontrarTodasLasEntradas(tipoEntrada).size();
        //Creacion de dos entradas
        //Entrada 1
        Entrada entrada1 = new Entrada();
        entrada1.setDni("09039698P");
        entrada1.setId(Integer.MAX_VALUE);
        entrada1.setNombreAsistente("nombreAsistente");
        entrada1.setTipoEntrada(tipoEntrada);
        entradaService.crearEntrada(entrada1, tipoEntrada.getId());
        //Entrada 2
        Entrada entrada2 = new Entrada();
        entrada2.setDni("74462193S");
        entrada2.setId(Integer.MAX_VALUE-1);
        entrada2.setNombreAsistente("nombreAsistente");
        entrada2.setTipoEntrada(tipoEntrada);
        entradaService.crearEntrada(entrada2, tipoEntrada.getId());
        //Numero de entradas después
        int numeroDeEntradasDespues = tipoEntradaService.EncontrarTodasLasEntradas(tipoEntrada).size();
        //Comprobación

        assertEquals(numeroDeEntradasAntes+2, numeroDeEntradasDespues);
    }

}
