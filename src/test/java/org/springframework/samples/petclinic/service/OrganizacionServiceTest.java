package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.repository.OrganizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OrganizacionServiceTest {
    @Autowired
    private OrganizacionService organizacionService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private OrganizacionRepository orgRepo;

    @Test
    public void testCountWithInitialDataOrganizational(){
        int count = organizacionService.organizacionCount();
        assertEquals(count, 0);
    }
    @Test
    public void deberiaListarMisEventos(){
        List<Evento> listaEventos= eventoService.listadoEventosDeOrganizacion(1);
        assertEquals(3, listaEventos.size());
    }
    @Test
    public void deberiaModificarMiPerfl(){
        Organizacion org= orgRepo.findById(1);
        Organizacion org2= orgRepo.findById(1);
        org2.setNombreOrganizacion("test");
        organizacionService.modifyUsuarioOrganizacion(org, org2);;
        assertEquals("test", org.getNombreOrganizacion());



    }
}
