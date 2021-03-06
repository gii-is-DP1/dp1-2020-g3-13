package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UsuarioService usuarioService;


    @Test
    public void testCountWithInitialDataAdmin(){
        int count = adminService.adminCount();
        assertEquals(count, 6);
    }
    @Test
    public void testMuestraUsuarios(){
        int count = usuarioService.usuarioCount();
        List<Usuario> actualList = new ArrayList<>();
        usuarioService.findAll().iterator().forEachRemaining(actualList::add);

        int counnt2= actualList.size();
        assertEquals(count, counnt2);
    }

    @Test
    public void testEliminarUsuario(){
        int count = usuarioService.usuarioCount();
        adminService.deleteUsuario("Test_delete");
        assertEquals(count-1, usuarioService.usuarioCount());
        assertEquals(null, usuarioService.findUsuario("Test_delete"));
    }
}
