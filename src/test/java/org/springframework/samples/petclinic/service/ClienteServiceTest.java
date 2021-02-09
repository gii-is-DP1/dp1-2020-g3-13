package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    @Test
    public void testCountWithInitialData(){
        int count= clienteService.clienteCount();
        assertEquals(count, 2);
    }

    @Test
    public void deberiaBorrarClienteTest(){
        int clienteAntes = clienteService.clienteCount();
        usuarioService.deleteUsuario(clienteService.findClienteByUsuario("Test_delete").getUsuario());
        clienteService.deleteCliente(clienteService.findClienteByUsuario("Test_delete"));
        int clienteDespues = clienteService.clienteCount();
        assertEquals(clienteAntes-1, clienteDespues);

    }

    @Test
    public void deberiaModificarClienteTest(){
        Cliente clienteActual = this.clienteService.findCliente().iterator().next();
        Cliente clienteConModificacion = clienteActual;
        clienteConModificacion.setApellidos("Apellido prueba");
        this.clienteService.modifyUsuarioCliente(clienteConModificacion, clienteActual);
        assertEquals("Apellido prueba",this.clienteService.findCliente().iterator().next().getApellidos());
    }

    @Test
    public void testCreateCliente(){
        int count = clienteService.clienteCount();
        Usuario usuario = new Usuario();
        usuario.setEnabled(true);
        usuario.setNombreUsuario("prueba");
        usuario.setPassword("Password8");

        Autoridades autoridades = new Autoridades();
        autoridades.setAutoridad("cliente");

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setApellidos("prueba");
        cliente.setEmail("prueba@gmail.com");
        cliente.setNombre("prueba");
        cliente.setTelefono("568748459");
        clienteService.saveCliente(cliente);
        int count2 = clienteService.clienteCount();
        assertEquals(count+1, count2);
        assertEquals(cliente, clienteService.findClienteByUsuario("prueba"));

    }
}