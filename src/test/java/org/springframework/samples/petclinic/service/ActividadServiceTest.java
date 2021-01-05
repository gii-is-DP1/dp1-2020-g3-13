package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Local;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActividadServiceTest {
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private LugarRealizacionService lugarRealizacionService;
    @Autowired
    private ActividadRepository actividadRepo;

    

    @Test
    public void testCountWithInitialDataActividad(){
        int count = actividadService.actividadesCount();
        assertEquals(count, 0);
    }
    @Test
    public void deberiaAñadirLugar(){
        Actividad act= new Actividad();
        actividadService.AñadirLugarRealizacionActividad(act, 1);
        assertEquals(1, act.getLugarRealizacion().getId());
    }
    @Test
    public void deberiaAñadirActividad(){
        Actividad act = new Actividad();
        act.setDescripcionActividad("descripcionActividad");
        act.setId(10);
        act.setFechaFin(LocalDate.now());
        act.setFechaInicio(LocalDate.now());
        act.setTematicaActividad("feliz");
        int count= actividadService.actividadesCount();
        actividadRepo.save(act);
        assertEquals(count+1, actividadService.actividadesCount());

    }
}
