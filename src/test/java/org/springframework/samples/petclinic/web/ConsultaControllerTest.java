package org.springframework.samples.petclinic.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.ConsultaService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@AutoConfigureMockMvc
@WebMvcTest(controllers = ConsultaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
//@excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
//@excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = ConsultaController.class)
public class ConsultaControllerTest {

    private static final String VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM = "consultas/nuevaConsulta";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @MockBean
    private ConsultaService consultaService;


    
    //Prueba a hacer la petición GET formulario de consultas
    @WithMockUser(value = "usuario")
    @Test 
    public void testFormularioCrearConsulta() throws Exception {
        mockMvc.perform(get("/consultas/1/nuevo")).andExpect(status().isOk())
        .andExpect(view().name(VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM)).andExpect(model().attributeExists("consulta"));
    }

    
    //Prueba a hacer la petición POST del formulario de consultas
    @Test    
    @WithMockUser(value = "usuario", authorities = "cliente")
    
    public void testCrearConsultaFormularioExito() throws Exception {   
        mockMvc.perform(post("/consultas/{id_evento}/nuevo", 1).param("asunto", "asuntillo").param("descripcion", "descripcion con varios caracteres")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos"));
    }

    @Test    
    @DisplayName("testCrearConsultaFormularioError")
    public void testCrearConsultaFormularioError() throws Exception {   
        mockMvc.perform(post("/consultas/{id_evento}/nuevo", 1).param("asunto", "asuntillo").param("descripcion", "descripcion con varios caracteres")).andExpect(status().isForbidden());
    }
    
}
