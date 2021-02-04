package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.InicializadorObjetosTest;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActividadServiceTest {
    
    @Autowired
    private ActividadService actividadService;

    @Test
    public void testCountWithInitialDataActividad() {
        int count = actividadService.actividadesCount();
        assertEquals(count, 0);
    }


    @Test
    public void deberiaAñadirActividad() {
        Actividad act = InicializadorObjetosTest.actividadParaTest();
        int count = actividadService.actividadesCount();
        actividadService.guardarActividad(act);
        assertEquals(count + 1, actividadService.actividadesCount());

    }

    @Test 
    public void deberiaTenerExponente(){
        Actividad act = InicializadorObjetosTest.actividadParaTest();
        Exponente exp = InicializadorObjetosTest.exponenteParaTest();
        act.getExponentes().add(exp);
        assertEquals(actividadService.contieneExponente(exp, act), true);
    }

    @Test
    public void deberiaAnadirActividadAEvento(){
        Evento evento = InicializadorObjetosTest.eventoParaTest();
        Actividad actividad = InicializadorObjetosTest.actividadParaTest();
        actividadService.anadirActividadAEvento(evento, actividad);
        assertEquals(evento.getActividades().iterator().next(), actividad);
        
    }
}
