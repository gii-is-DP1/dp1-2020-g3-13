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
public class ExponenteSecurityControllerTest {

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;

    private int TEST_EVENTO_ID = 1;
    private int TEST_ACTIVIDAD_ID =2;
    private int TEST_EXPONENTE_ID =1;

    @BeforeEach
    void setup() {
       mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @WithMockUser(username = "clienteRandom", authorities = {"cliente"})
    @Test
    void noPuedeCrearExponente() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades/{actividad_id}/nuevo",TEST_EVENTO_ID,TEST_ACTIVIDAD_ID)).andExpect(status().isForbidden());
    }

    @WithMockUser(username = "organizacion1", authorities = {"organizacion"})
    @Test
    void creaExponenteForm() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades/{actividad_id}/nuevo",TEST_EVENTO_ID,TEST_ACTIVIDAD_ID)).andExpect(status().isOk())
        .andExpect(view().name("exponentes/crearExponentes")).andExpect(model().attributeExists("exponente"));
    }

    @WithMockUser(username = "organizacion1", authorities = {"organizacion"})
    @Test
    void creaExponentePasa() throws Exception{
        mockMvc.perform(post("/eventos/{evento_id}/actividades/{actividad_id}/nuevo",TEST_EVENTO_ID,TEST_ACTIVIDAD_ID)
        .param("nombreExponente", "Exponente").param("apellidosExponente", "De Prueba Garcia")
        .param("alias", "pruebecita").with(csrf())).andExpect(status().isOk())
        .andExpect(view().name("exponentes/crearExponentes"));
    }


    @WithMockUser(username = "organizacion1", authorities = {"organizacion"})
    @Test 
    void borrarExponentePasa() throws Exception{
        mockMvc.perform(get( "/eventos/{evento_id}/actividades/{actividad_id}/{exponente_id}/borrarExponente",TEST_EVENTO_ID,TEST_ACTIVIDAD_ID,TEST_EXPONENTE_ID)).andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = "cliente1", authorities = {"cliente"})
    @Test 
    void borrarExponenteNoPasa() throws Exception{
        mockMvc.perform(get( "/eventos/{evento_id}/actividades/{actividad_id}/{exponente_id}/borrarExponente",TEST_EVENTO_ID,TEST_ACTIVIDAD_ID,TEST_EXPONENTE_ID)).andExpect(status().isForbidden());
    }
    }

