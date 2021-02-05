package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ExtendWith(SpringExtension.class)
public class EntradaSecurityControllerTest {

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @MockBean
    private EntradaService entradaService;
    @MockBean
    private CarritoService carritoService;
    @MockBean
    private ClienteService clienteService;

    private int TEST_EVENTO_ID = 8;
    private int TEST_ENTRADA_ID = 1;
    @BeforeEach
    void setup() {
       mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @WithMockUser(username="organizacionRandom",authorities = {"organizacion"})
    @Test
    void noPuedeCrearEntradaForm() throws Exception {
        mockMvc.perform(get("/eventos/{eventoId}/{tipoEntradasId}/entrada",TEST_EVENTO_ID,TEST_ENTRADA_ID))
        .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @WithMockUser(username="clienteRandom",authorities = {"cliente"})
    @Test
    void testCrearEntradaForm() throws Exception {
        mockMvc.perform(get("/eventos/{eventoId}/{tipoEntradasId}/entrada",TEST_EVENTO_ID,TEST_ENTRADA_ID)).andExpect(status().isOk())
        .andExpect(view().name("entradas/crearEntrada")).andExpect(model().attributeExists("entrada"));
    }

    @WithMockUser(username="clienteRandom",authorities = {"cliente"})
    @Test
    void postEntrada() throws Exception{
        mockMvc.perform(post("/eventos/8/1/entrada").param("dni", "77778888H").param("nombreAsistente", "Raul Rodriguez Mendez").with(csrf()))
             .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos/{eventoId}"));
    }

    // @WithMockUser(username="clienteRandom",authorities = {"cliente"})
    // @Test
    // void postEntradaTieneErrores() throws Exception{
    //     mockMvc.perform(post("/eventos/8/1/entrada").param("nombreAsistente", "Raul Rodriguez Mendez").with(csrf()))
    //          .andExpect(model().attributeHasFieldErrors("entrada", "dni")).andExpect(view().name("redirect:/eventos/{eventoId}"));
    // }


}
