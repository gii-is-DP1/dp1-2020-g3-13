package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Consulta;
import org.springframework.samples.petclinic.model.InicializadorObjetosTest;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ConsultaServiceTest {
    @Autowired
    private ConsultaService consultaService;

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
}
