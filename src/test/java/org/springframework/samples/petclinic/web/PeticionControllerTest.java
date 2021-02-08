package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.PeticionService;
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
import org.springframework.samples.petclinic.service.EnvioEmailService;
import org.springframework.samples.petclinic.service.OrganizacionService;

@WebMvcTest(controllers = PeticionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = PeticionController.class)
public class PeticionControllerTest {

    private static final String VIEWS_PETICION = "peticion/CreatePeticionForm";
    private static final String VIEWS_LISTADO_PETICION = "/peticion/listado";
    private static final String VIEWS_DETALLES_PETICION = "peticion/listadoDetails";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeticionService peticionService;

    @MockBean
    private AutoridadesService autoridadesService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private EnvioEmailService envioEmailService;

    @Test
    @WithMockUser
    public void testCrearPeticionFormularioExito() throws Exception {
        mockMvc.perform(post("/peticion/new", 1).param("email", "email@email.com")
                .param("nombre_organizacion", "el nombre de la organizacion").param("cif", "12345678")
                .param("info", "informacion de la organizacion"));

    }

    @Test
    @WithMockUser
    public void testFormularioCrearPeticion() throws Exception {
        mockMvc.perform(get("/peticion/new")).andExpect(status().isOk()).andExpect(view().name(VIEWS_PETICION))
                .andExpect(model().attributeExists("peticion"));
    }

    @Test
    @WithMockUser
    public void testListadoPeticiones() throws Exception {
        mockMvc.perform(get("/peticion")).andExpect(status().isOk()).andExpect(view().name(VIEWS_LISTADO_PETICION))
                .andExpect(model().attributeExists("peticion"));
    }

    // @Test
    // @WithMockUser
    // public void testDetallesPeticion() throws Exception {
    // mockMvc.perform(get("/peticion/{peticion_id}", 1)).andExpect(status().isOk())
    // .andExpect(view().name(VIEWS_DETALLES_PETICION)).andExpect(model().attributeExists("peticion"));
    // }
}