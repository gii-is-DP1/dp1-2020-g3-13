package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Carrito;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class CarritoServiceTest {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private FacturaService facturaService;

    @Test
    public void deberiaAnadirAlCarrito(){
        Evento eventoPrueba = eventoService.findAll().iterator().next();
        Usuario usuario = usuarioService.findUsuario("cliente1");
        Cliente cliente = clienteService.findClienteByUsuario(usuario.getNombreUsuario());
        Entrada entradaPrueba = new Entrada();
        List<TipoEntrada> LTE= eventoService.getTipoEntradaPorEvento(eventoPrueba.getId());
        entradaPrueba.setTipoEntrada(LTE.get(1));
        entradaPrueba.setCliente(cliente);
        entradaPrueba.setDni("49063639Q");
        entradaPrueba.setNombreAsistente("Persona Prueba");
      
        carritoService.anadirCarrito(entradaPrueba, cliente);
        Carrito carritoPrueba= carritoService.dimeCarritoUsuario(usuario.getNombreUsuario());
        Integer elementosCarrito = carritoService.contadorElementosCarrito(carritoPrueba);
        assertEquals(1, elementosCarrito);
    }
    @Test
    public void deberiaGenerarFactura(){
        Integer contFacturasInicial = facturaService.facturaCount();
        Evento eventoPrueba = eventoService.findAll().iterator().next();
        Usuario usuario = usuarioService.findUsuario("cliente1");
        Cliente cliente = clienteService.findClienteByUsuario(usuario.getNombreUsuario());
        List<TipoEntrada> LTE= eventoService.getTipoEntradaPorEvento(eventoPrueba.getId());

        Integer tipoEntradaPruebaId = LTE.get(1).getId();
        Entrada entradaPrueba = new Entrada();
        entradaPrueba.setTipoEntrada(LTE.get(1));
        entradaPrueba.setCliente(cliente);
        entradaPrueba.setDni("49063639Q");
        entradaPrueba.setNombreAsistente("Persona Prueba");
        entradaService.crearEntrada(entradaPrueba, tipoEntradaPruebaId,cliente);
        carritoService.anadirCarrito(entradaPrueba, cliente);
        Carrito carritoPrueba= carritoService.dimeCarritoUsuario(usuario.getNombreUsuario());
        carritoService.generarFacturaCarrito(carritoPrueba, cliente);
        Integer contFacturasActual = facturaService.facturaCount();
        assertEquals(contFacturasInicial+1, contFacturasActual);


    }
}