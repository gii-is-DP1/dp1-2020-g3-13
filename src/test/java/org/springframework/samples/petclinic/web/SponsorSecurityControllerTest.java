package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SponsorSecurityControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private int TEST_ID = 1;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    // CON USUARIO ORGANIZACION LOGEADO
    @WithMockUser(username = "UsuarioAleatorio", authorities = { "organizacion" })
    @Test
    void deberiaPoderCrearSponsor() throws Exception {
        mockMvc.perform(get("/eventos/{evento_id}/sponsors/nuevo", TEST_ID)).andExpect(status().isOk())
                .andExpect(view().name("sponsors/crearNuevoSponsor")).andExpect(model().attributeExists("sponsor"));

    }

    // CON USUARIO CLIENTE LOGEADO
    @WithMockUser(username = "UsuarioAleatorio", authorities = { "cliente" })
    @Test
    void deberiaDevolverProhibidoCrearSponsorParaCliente() throws Exception {
        mockMvc.perform(get("/eventos/{evento_id}/sponsors/nuevo", TEST_ID)).andExpect(status().isForbidden());

    }

    // CON USUARIO ADMIN LOGEADO
    @WithMockUser(username = "UsuarioAleatorio", authorities = { "admin" })
    @Test
    void deberiaDevolverProhibidoCrearSponsorParaAdmin() throws Exception {
        mockMvc.perform(get("/eventos/{evento_id}/sponsors/nuevo", TEST_ID)).andExpect(status().isForbidden());

    }

    // SIN USUARIO LOGEADO
    @WithMockUser
    @Test
    void deberiaDevolverProhibidoCrearSponsorParaUsuarioNoLogeado() throws Exception {
        mockMvc.perform(get("/eventos/{evento_id}/sponsors/nuevo", TEST_ID)).andExpect(status().isForbidden());

    }
}