package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.springframework.stereotype.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PeticionServiceTest {
    @Autowired
    private PeticionService peticionService;
    @Test
    public void testCountWithInitialData(){
        int count= peticionService.peticionCount();
        assertEquals(count, 0);
    }
}
