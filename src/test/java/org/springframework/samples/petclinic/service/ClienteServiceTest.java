package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private UsuarioService usuarioService;
    @Test
    public void testCountWithInitialData(){
        int count= clienteService.clienteCount();
        assertEquals(count, 0);
    }

    @Test
    public void deleteClienteTest(){
        Integer clienteAntes = (int) clienteRepo.count();
        clienteService.deleteCliente(clienteService.findClienteByUsuario("prueba1"));
        Integer clienteDespues = (int) clienteRepo.count();
        assertEquals(clienteAntes-1, clienteDespues);

    }

    @Test
    public void modifyClienteTest(){
        Cliente clienteActual = this.clienteService.findCliente().iterator().next();
        Cliente clienteConModificacion = clienteActual;
        clienteConModificacion.setApellidos("Apellido prueba");
        this.clienteService.modifyUsuarioCliente(clienteConModificacion, clienteActual);
        assertEquals("Apellido prueba",this.clienteService.findCliente().iterator().next().getApellidos());
    }
}