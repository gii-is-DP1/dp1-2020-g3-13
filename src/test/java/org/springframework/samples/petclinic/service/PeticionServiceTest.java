package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Peticion;

// @SpringBootTest(classes= {PeticionService.class, EnvioEmailService.class, JavaMailSender.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PeticionServiceTest {
    @Autowired
    private PeticionService peticionService;
    @Autowired
    private OrganizacionService organizacionService;
    @Test
    public void testCountWithInitialData(){
        int count= peticionService.peticionCount();
        assertEquals(count, 0);
    }
  
    @Test
    public void shouldFindPeticiones(){
        int a = (int) StreamSupport.stream(peticionService.dimeTodas().spliterator(), false).count();
        int b = peticionService.peticionCount();
        assertEquals(b, a);
    }

    @Test
    public void deberiaCrearPeticiónTest(){
        int antes = peticionService.peticionCount();
        Peticion peti = new Peticion();
        peti.setNombre_organizacion("pacopepe");
        peti.setFecha(LocalDate.now());
        peti.setCif("J76767676");
        peti.setEmail("email@email.email");
        peti.setInfo("info");
        peticionService.createPeticion(peti);
        int ahora = peticionService.peticionCount();
        assertEquals(ahora, antes+1);
        
    }
    @Test
    public void deberíaEliminarPeticionTest(){
        int prev = peticionService.peticionCount();
        peticionService.deletePeticion(peticionService.findPeticionById(1).get());
        assertEquals(prev-1, peticionService.peticionCount());
    
    }
    @Test
    void deberíaCrearOrganizacionAPartirDePeticionTest(){ 
        int organizacionAntes = organizacionService.organizacionCount();
        Peticion peti = new Peticion();
        peti.setNombre_organizacion("pacopepe");
        peti.setFecha(LocalDate.now());
        peti.setCif("J76767676");
        peti.setEmail("email@email.email");
        peti.setInfo("info");
        peticionService.createPeticion(peti);
        int organizacionDespues = organizacionService.organizacionCount();
        assertEquals(organizacionAntes+1, organizacionDespues);
    }
}