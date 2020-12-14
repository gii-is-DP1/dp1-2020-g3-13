package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.repository.LugarRealizacionRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LugarRealizacionServiceTest {

@Autowired
private LugarRealizacionService lugarService;
@Autowired
private LugarRealizacionRepository lugarRepo;

    @Test
    public void shouldFindLugares(){
        Long a = StreamSupport.stream(lugarService.findAll().spliterator(), false).count();
        Long b =lugarRepo.count();
        assertEquals(b, a);
    }
}
