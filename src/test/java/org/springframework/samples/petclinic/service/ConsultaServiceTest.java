package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ConsultaServiceTest {
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCountWithInitialDataConsulta() {
        int count = consultaService.consultasCount();
        assertEquals(count, 0);
    }

    @Test
    public void testNuevaConsulta() {
        Consulta consulta = new Consulta();
        consulta.setAsunto("asunto");
        consulta.setDescripcion("esta es la descripcion");
        int consultasAntes = consultaService.consultasCount();
        Cliente cliente = new Cliente();
        cliente.setApellidos("Apellidos");
        cliente.setEmail("ejemplo@email.com");
        cliente.setNombre("nombre");
        cliente.setTelefono("123445623");
        consultaService.anadirConsulta(consulta, 4, cliente);
        int consultasDespues = consultaService.consultasCount();
        assertEquals(consultasAntes + 1, consultasDespues);

    }

    @Test
    public void testNuevaRespuesta() {
        Consulta consulta = new Consulta();
        consulta.setAsunto("asunto2");
        consulta.setDescripcion("esta es la segunda descripcion");
        Organizacion org = organizacionService.findAll().iterator().next();
        // consulta.setEvento(org.getEventos().get(0));
        consulta.setEvento(organizacionService.getEventos(org.getId()).get(0));
        consultaService.anadirConsulta(consulta, consulta.getEvento().getId(),
                clienteService.findCliente().iterator().next());
        assertEquals(null, consulta.getRespuesta());
        Consulta consultaConRespuesta = new Consulta();
        consultaConRespuesta.setRespuesta("Esto es un ejemplo de respuesta para una consulta realizada por un cliente");
        consultaService.aniadirRespuesta(consultaConRespuesta, consulta.getId());
        assertEquals("Esto es un ejemplo de respuesta para una consulta realizada por un cliente",
                consulta.getRespuesta());

    }

}
