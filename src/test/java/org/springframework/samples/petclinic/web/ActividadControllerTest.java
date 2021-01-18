package org.springframework.samples.petclinic.web;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.service.ConsultaService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@SpringBootTest
@AutoConfigureMockMvc
public class ActividadControllerTest {
    
    private static final String VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM = "actividades/crearActividad";
    private static final String VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES = "actividades/EventoLista";


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
    //    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @WithMockUser(value = "usuario")
    @Test
    public void testListaActividad() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades",1)).andExpect(status().isOk()).andExpect(view().name(VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES)).andExpect(model().attributeExists("actividades"));
    }

    
    @Test
    public void testFormularioActividad() throws Exception{

        mockMvc.perform(get("/eventos/{evento_id}/actividades/nuevo", 1)).andExpect(status().isOk()).andExpect(view().name(VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM)).andExpect(model().attributeExists("listaId","lugaresRealizacion","actividad"));
    }
    //Terminar
    @Test
    public void testFormularioActividadExito() throws Exception{
        mockMvc.perform(post("/eventos/{evento_id}/actividades/nuevo", 1).param("tematicaActividad", "tematica de ejemplo").param("descripcionActividad", "una descripcion cualquiera que cumpla con el validador").param("fechaInicio", "2021/02/02 09:00").param("fechaFin", "2021/02/02 12:00").param("lugarRealizacion", "1")).andExpect(view().name("VISTA"));
    }
}
