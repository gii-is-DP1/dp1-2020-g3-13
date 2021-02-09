package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.LineaFacturaService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(controllers = CarritoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = CarritoController.class)

public class CarritoControllerTest {

    
    private static final String VIEW_CARRITO_CLIENTE = "carrito/miCarrito";
    private static final String VIEW_CARRITO_ORGANIZACION = "carrito/miCarritoOrganizacion";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private LineaFacturaService lineasFacturaService;

    @MockBean
    private AutoridadesService autoridadesService;
    
    @MockBean
    private ActividadService actividadService;

    @MockBean
    private EntradaService entradaService;


    @WithMockUser
    @Test
    public void testCarritoCliente() throws Exception {
        mockMvc.perform(get("/carrito/cliente")).andExpect(status().isOk())
        .andExpect(view().name(VIEW_CARRITO_CLIENTE));
    }
    
    @WithMockUser
    @Test 
    public void testCarritoOrganizacion() throws Exception {
        mockMvc.perform(get("/carrito/organizacion")).andExpect(status().isOk())
        .andExpect(view().name(VIEW_CARRITO_ORGANIZACION));
    }

    
    
}
