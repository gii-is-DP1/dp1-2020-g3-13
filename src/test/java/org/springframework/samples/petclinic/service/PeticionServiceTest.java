package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.samples.petclinic.model.Peticion;
import org.springframework.samples.petclinic.repository.PeticionRepository;

// @SpringBootTest(classes= {PeticionService.class, EnvioEmailService.class, JavaMailSender.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PeticionServiceTest {
    @Autowired
    private PeticionService peticionService;
    @Autowired
    private PeticionRepository peticionRepo;
    @Test
    public void testCountWithInitialData(){
        int count= peticionService.peticionCount();
        assertEquals(count, 0);
    }
  
    @Test
    public void shouldFindPeticiones(){
        Long a = StreamSupport.stream(peticionService.dimeTodas().spliterator(), false)
                        .count();
        Long b =peticionRepo.count();
        assertEquals(b, a);
    }
}

