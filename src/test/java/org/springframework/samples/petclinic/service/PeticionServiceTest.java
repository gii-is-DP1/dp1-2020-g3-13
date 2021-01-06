package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
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

    @Test
    public void createPeticionTest(){
        Integer antes = (int) peticionRepo.count();
        Peticion peti = new Peticion();
        peti.setNombre_organizacion("pacopepe");
        peti.setFecha(LocalDate.now());
        peti.setCif("cif");
        peti.setEmail("email@email.email");
        peti.setInfo("info");
        peticionService.createPeticion(peti);
        Integer ahora = (int) peticionRepo.count();
        assertEquals(ahora, antes+1);
        
    }
    @Test
    public void deletePeticionTest(){
        Integer prev=  (int) peticionRepo.count();
        peticionRepo.delete(peticionService.findPeticionById(1).get());
        assertEquals(prev-1, (int)peticionRepo.count());
    
    }
}