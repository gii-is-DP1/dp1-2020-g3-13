package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ExponenteServiceTest {

    
        @Autowired
        private ExponenteService exponenteService;
        @Autowired
        private EventoService eventoService;
        @Autowired
        private ActividadService actividadService;

        @BeforeEach
        void setup(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("ExpoPruebas");
            Actividad actividadExpo = new Actividad();
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
            evento.setTipoEvento("tipoEvento");
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);
            
        }
    
        @Test
        public void deberiaEncontrarExponente(){
            Exponente exponente = new Exponente();
            exponente.setNombreExponente("Exponente");
            exponente.setApellidosExponente("De Prueba");
            exponente.setAlias("ExpoPruebas");
            Actividad actividadExpo = new Actividad();
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
            evento.setTipoEvento("tipoEvento");
            eventoService.save(evento);
            actividadExpo.setTematicaActividad("ActividadPrueba");
            actividadExpo.setDescripcionActividad("ESTO ES UNA ACTIVIDAD DE PRUEBA CON UNA DESCRIPCION APROPIADA PARA LA PRUEBA");
            actividadExpo.setFechaFin(LocalDateTime.of(2022, 03, 03, 10, 00));
            actividadExpo.setFechaInicio(LocalDateTime.of(2022, 03, 02, 10, 00));
            actividadExpo.setEvento(evento);
            actividadService.guardarActividad(actividadExpo);
            exponenteService.guardarExponente(exponente);
            Exponente pruebaMetodo = exponenteService.buscaExponente(exponente);
            assertEquals(exponente, pruebaMetodo);
        }
    //     @Test
    //     public void deberiaGenerarFactura(){
    //         Integer contFacturasInicial = facturaService.facturaCount();
    //         Evento eventoPrueba = eventoService.findAll().iterator().next();
    //         Usuario usuario = usuarioService.findUsuario("cliente1");
    //         Cliente cliente = clienteService.findClienteByUsuario(usuario.getNombreUsuario());
    //         Integer tipoEntradaPruebaId = eventoPrueba.getTipoEntradas().get(1).getId();
    //         Entrada entradaPrueba = new Entrada();
    //         entradaPrueba.setCliente(cliente);
    //         entradaPrueba.setDni("00000000L");
    //         entradaPrueba.setNombreAsistente("Persona Prueba");
    //         entradaService.crearEntrada(entradaPrueba, tipoEntradaPruebaId);
    //         carritoService.anadirCarrito(entradaPrueba, cliente);
    //         Carrito carritoPrueba= carritoService.dimeCarritoUsuario(usuario.getNombreUsuario());
    //         carritoService.generarFacturaCarrito(carritoPrueba, cliente);
    //         Integer contFacturasActual = facturaService.facturaCount();
    //         assertEquals(contFacturasInicial+1, contFacturasActual);
    
    
    //     }
    // }   
}
