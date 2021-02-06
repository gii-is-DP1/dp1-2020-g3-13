package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.LugarRealizacion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LugarRealizacionServiceTest {

@Autowired
private LugarRealizacionService lugarService;

    @Test
    public void shouldFindLugares(){
        int a = (int) StreamSupport.stream(lugarService.findAll().spliterator(), false).count();
        int b = lugarService.lugaresCount();
        assertEquals(b, a);
    }

    @Test 
    public void testCrearLugares(){
        int count = lugarService.lugaresCount();
        LugarRealizacion lugar = new LugarRealizacion();
        lugar.setAforo(41);
        lugar.setCaracteristicas("caracteristicas");
        lugar.setDireccion("direccion");
        lugar.setEmail("email@email.com");
        lugar.setNombre_recinto("nombre_recinto");
        lugar.setTelefono("786765887");
        lugarService.saveLugarRealizacion(lugar);
        assertEquals(count+1, lugarService.lugaresCount());
        assertEquals(lugar, lugarService.findById(lugar.getId()));
    }
    @Test
    public void testModificaLugares(){
        LugarRealizacion lRealizacion= lugarService.findById(1);
        lRealizacion.setAforo(3013);
        lugarService.saveLugarRealizacion(lRealizacion);
        assertEquals(3013, lRealizacion.getAforo());
    }
}
