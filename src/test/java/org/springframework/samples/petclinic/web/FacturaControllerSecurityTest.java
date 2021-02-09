package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ExtendWith(SpringExtension.class)
public class FacturaControllerSecurityTest {

    private static final int TEST_FACTURA_CLIENTE_ID = 7;
    private static final int TEST_FACTURA_ORGANIZACION_ID = 5;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @WithMockUser(username = "clienteRandom", authorities = { "cliente" })
    @Test
    void accederAFacturasCliente() throws Exception {
        mockMvc.perform(get("/facturas")).andExpect(status().isOk()).andExpect(view().name("facturas/listadoFacturas"))
                .andExpect(model().attributeExists("facturas"));
    }

    @WithMockUser(username = "orgRandom", authorities = { "organizacion" })
    @Test
    void accederAFacturasOrg() throws Exception {
        mockMvc.perform(get("/facturas")).andExpect(status().isOk()).andExpect(view().name("facturas/listadoFacturas"))
                .andExpect(model().attributeExists("facturas"));
    }

    @WithMockUser(username = "organizacion1", authorities = { "organizacion" })
    @Test
    void accederADetallesFacturasOrg() throws Exception {
        mockMvc.perform(get("/facturas/{facturaId}",TEST_FACTURA_ORGANIZACION_ID)).andExpect(status().isOk())
                .andExpect(view().name("facturas/detallesFacturaOrg")).andExpect(model().attributeExists("facturas"));
    }

    @WithMockUser(username = "cliente1", authorities = { "cliente" })
    @Test
    void accederADetallesFacturasCliente() throws Exception {
        mockMvc.perform(get("/facturas/{facturaId}",TEST_FACTURA_CLIENTE_ID)).andExpect(status().isOk())
                .andExpect(view().name("facturas/detallesFacturasCliente"))
                .andExpect(model().attributeExists("facturas"));
    }
}
