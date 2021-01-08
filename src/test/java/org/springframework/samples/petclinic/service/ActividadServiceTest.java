package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActividadServiceTest {
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private ActividadRepository actividadRepo;

    @Test
    public void testCountWithInitialDataActividad() {
        int count = actividadService.actividadesCount();
        assertEquals(count, 0);
    }

    @Test
    public void deberiaAñadirLugar() {
        Actividad act = new Actividad();
        actividadService.AñadirLugarRealizacionActividad(act, 1);
        assertEquals(1, act.getLugarRealizacion().getId());
    }

    @Test
    public void deberiaAñadirActividad() {
        Actividad act = new Actividad();
        act.setTematicaActividad("Lorem ipsum");
        act.setDescripcionActividad("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam faucibus, lacus ut convallis suscipit, leo lectus porttitor felis, dictum pharetra ante eros eu dolor. Praesent aliquet accumsan tincidunt. Fusce vel accumsan orci, volutpat vehicula turpis. Suspendisse iaculis convallis varius. Sed vitae ipsum eros. Quisque vel lectus varius, varius massa at, maximus dui.");
        act.setId(Integer.MAX_VALUE);
        act.setFechaInicio(LocalDate.now().plusDays(1));
        act.setFechaFin(LocalDate.now().plusDays(2));
        int count = actividadService.actividadesCount();
        actividadRepo.save(act);
        assertEquals(count + 1, actividadService.actividadesCount());

    }

    @Test 
    public void deberiaTenerExponente(){
        Actividad act = actividadService.findAll().iterator().next();
        Exponente exp = new Exponente();
        exp.setAlias("alias");
        exp.setNombreExponente("nombreExponente");
        exp.setApellidosExponente("apellidosExponente");
        act.getExponentes().add(exp);
        assertEquals(actividadService.contieneExponente(exp, act), true);
    }
}
