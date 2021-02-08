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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ActividadControllerSecurityTest {
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;

    private int TEST_ID = 1;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
    @Test
    @WithMockUser(username = "UsuarioAleatorio", authorities = {"cliente"})
    void deberiaDevolverListaDeActividadesCliente() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades/{actividad_id}",1,2, TEST_ID)).andExpect(status().isForbidden());
        
    }
    @Test
    @WithMockUser(username = "UsuarioAleatorio", authorities = {"organizacion"})
    void deberiaDevolverListaDeActividadesOrganizacion() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades/{actividad_id}",1,2, TEST_ID)).andExpect(view().name("actividades/detallesActividad"));
        
    }
    @Test
    @WithMockUser(username = "UsuarioAleatorio", authorities = {"admin"})
    void deberiaDevolverListaDeActividadesAdmin() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades/{actividad_id}",1,2, TEST_ID)).andExpect(view().name("actividades/detallesActividad"));
        
    }
    
}
