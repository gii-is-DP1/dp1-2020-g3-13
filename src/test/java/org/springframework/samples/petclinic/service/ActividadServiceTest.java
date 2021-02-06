package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.model.InicializadorObjetosTest;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActividadServiceTest {
    
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private ExponenteService expoService;
    @Autowired
    private EventoRepository eventoRepo;

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

    // @Test 
    // public void deberiaTenerExponente(){
    //     Actividad act = InicializadorObjetosTest.actividadParaTest();
    //     Exponente exp = InicializadorObjetosTest.exponenteParaTest();
    //     List<Actividad> actividad = exp.getActividades();
    //     actividad.add(act);
    //     exp.setActividades(actividad);
    //     expoService.guardarExponente(exp);
    //     // act.getExponentes().add(exp);
    //     assertEquals(actividadService.contieneExponente(exp, act), true);
    // }

    @Test
    public void deberiaAnadirActividadAEvento(){
        Evento evento = InicializadorObjetosTest.eventoParaTest();
        Actividad actividad = InicializadorObjetosTest.actividadParaTest();
        actividadService.anadirActividadAEvento(evento, actividad);
        assertEquals(eventoRepo.getActividades(evento.getId()).iterator().next(), actividad);
        
    }
}
