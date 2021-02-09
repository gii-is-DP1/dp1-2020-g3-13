package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.PeticionService;
import org.springframework.samples.petclinic.service.VentaEntradaService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.samples.petclinic.service.OrganizacionService;

@WebMvcTest(controllers = VentaEntradaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = VentaEntradaController.class)
public class VentaEntradaControllerTest {

    private static final String VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM = "eventos/createVentaEntrada";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeticionService peticionService;

    @MockBean
    private AutoridadesService autoridadesService;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private VentaEntradaService ventaEntradaService;

    @MockBean
    private EntradaService entradaService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private CarritoService carritoService;

    @Test
    @WithMockUser
    public void testVentaEntradaFormularioExito() throws Exception {
        mockMvc.perform(post("/carrito/finalizarCompra", 1).param("nombreTitular", "julibamon")
                .param("numTarjeta", "1111111111111111").param("fechaCaducidad", "2021/02/10").param("cvv", "123"));

    }

    @Test
    @WithMockUser
    public void testVentaEntradaFormularioNoExito() throws Exception {
        mockMvc.perform(post("/carrito/finalizarCompra", 1).param("nombreTitular", "julibamon")
                .param("numTarjeta", "1111111111111111").param("fechaCaducidad", "2021/02/10").param("cvv", "123"))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser
    public void testVentaEntradaGET() throws Exception {
        mockMvc.perform(get("/carrito/finalizarCompra")).andExpect(status().isOk())
                .andExpect(view().name(VIEWS_VENTA_ENTRADAS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("ventaEntrada"));
    }

}
