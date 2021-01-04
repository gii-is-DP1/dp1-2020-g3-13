package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrganizacionService organizacionService;

    @Test
    public void testCountWithInitialDataConsulta(){
        int count = facturaService.facturaCount();
        assertEquals(count, 5);
    }

    @Test
    public void deberiaMostrarFactura(){
        Usuario usuario = usuarioService.findUsuario("organizacion1");
        List<Factura> facts = usuario.getFacturas();
        Integer tam = facts.size();
        assertEquals(1, tam);

    }
}
