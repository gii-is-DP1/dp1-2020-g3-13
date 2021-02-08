package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.InicializadorObjetosTest;
import org.springframework.samples.petclinic.repository.ActividadRepository;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActividadServiceTest {
    
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private ActividadRepository actividadRepo;
    @Autowired
    private EventoService eventoService;

    @Test
    public void testCountWithInitialDataActividad() {
        int count = actividadService.actividadesCount();
        assertEquals(count, 3);
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
        Actividad actividad = new Actividad();
        actividad.setTematicaActividad("Cine");
        actividad.setDescripcionActividad("Peliculas de terror");
        actividad.setFechaInicio(LocalDateTime.now().plusDays(2));
        actividad.setFechaFin(LocalDateTime.now().plusDays(3));
        
        actividadRepo.save(actividad);
        actividadService.anadirActividadAEvento(evento, actividad);
        
        assertEquals(eventoService.getActividades(evento.getId()).iterator().next(), actividad);
        
    }
}
