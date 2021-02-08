package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Exponente;
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
    @Autowired
    private ExponenteService expoService;

    @Test
    public void testCountWithInitialDataActividad() {
        int count = actividadService.actividadesCount();
        assertEquals(count, 3);
    }


    @Test
    public void deberiaAñadirActividadAEvento() {
        Evento eventoPrueba = eventoService.findAll().iterator().next();
        Actividad act = InicializadorObjetosTest.actividadParaTest();
        actividadService.anadirActividadAEvento(eventoPrueba, act);
      
        actividadService.guardarActividad(act);
        eventoService.save(eventoPrueba);
        assertEquals(act.getTematicaActividad(),actividadService.encuentraActividadesPorEvento(eventoPrueba.getId()).get(2).getTematicaActividad());

    }
    @Test
    public void deberiaModificarActividad(){
        Evento eventoPrueba = eventoService.findAll().iterator().next();
        Actividad actPrevia= actividadService.encuentraActividadesPorEvento(eventoPrueba.getId()).get(1);
        Actividad actPost= actPrevia;
        actPost.setDescripcionActividad("que me gusta hacer test me guta me guta");
        actividadService.modificarActividad(actPrevia , actPost);
        assertEquals(actividadService.encuentraActividadesPorEvento(eventoPrueba.getId()).get(1).getDescripcionActividad(), "que me gusta hacer test me guta me guta");
    }

    

    @Test
    public void deberiaEncontrarExponente(){
        Evento eventoPrueba = eventoService.findAll().iterator().next();
        Actividad actPrevia= actividadService.encuentraActividadesPorEvento(eventoPrueba.getId()).get(1);
        Exponente exp= new Exponente();
        exp.setAlias("paquito");
        exp.setApellidosExponente("tesito");
        exp.setNombreExponente("me guta");
        exp.setId(42112);
        
        expoService.anadirExponente(actPrevia, exp);
        assertEquals(true, actividadService.contieneExponente(actPrevia.getExponentes().get(0), actPrevia)); actividadService.contieneExponente(actPrevia.getExponentes().get(0), actPrevia);
    }

   
}
