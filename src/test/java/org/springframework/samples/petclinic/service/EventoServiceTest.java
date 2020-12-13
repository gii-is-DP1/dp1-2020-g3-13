package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Provider.Service;

import org.junit.Test;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EventoServiceTest {
    @Autowired
    private EventoService eventoService;

    @Test
    public void testCountWithInitialData() {
        int count = eventoService.eventosCount();
        assertEquals(count, 5);
    }
}
