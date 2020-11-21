package org.springframework.samples.petclinic.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ConsultaServiceTest {
    @Autowired
    private ConsultaService consultaService;

    @Test
    public void testCountWithInitialDataConsulta(){
        int count = consultaService.consultasCount();
        assertEquals(count, 0);
    }
}
