package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OrganizacionServiceTest {
    @Autowired
    private OrganizacionService organizacionService;

    @Test
    public void testCountWithInitialDataOrganizational(){
        int count = organizacionService.organizacionCount();
        assertEquals(count, 0);
    }
    @Test
    public void deberiaModificarMiPerfl(){
        Organizacion org = organizacionService.findOrganizacionById(1);
        Organizacion org2 = organizacionService.findOrganizacionById(1);
        org2.setNombreOrganizacion("test");
        organizacionService.modifyUsuarioOrganizacion(org, org2);;
        assertEquals("test", org.getNombreOrganizacion());
    }

    @Test
    public void deberiaEliminarMiPerfil(){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("nombreUsuario");
        usuario.setPassword("passwordAd23");
        Organizacion organizacion = new Organizacion();
        organizacion.setCif("J87878787");
        organizacion.setEmail("email@email.com");
        organizacion.setId(1);
        organizacion.setInfo("info de una organizacion cualquiera");
        organizacion.setNombreOrganizacion("nombreOrganizacion");
        organizacion.setUsuario(usuario);
        organizacionService.saveOrganizacion(organizacion);
        int cuentaOrganizacionAntes = organizacionService.organizacionCount();
        organizacionService.deleteOrganizacion(organizacion);
        int cuentaOrganizacionDespues = organizacionService.organizacionCount();
        assertEquals(cuentaOrganizacionAntes-1, cuentaOrganizacionDespues);
        
    }
}
