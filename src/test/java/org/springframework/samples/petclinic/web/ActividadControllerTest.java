package org.springframework.samples.petclinic.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Exponente;
import org.springframework.samples.petclinic.service.ActividadService;
import org.springframework.samples.petclinic.service.AlquilerEspacioService;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.ExponenteService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ActividadController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = ActividadController.class)

public class ActividadControllerTest {
    
    private static final String VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM = "actividades/crearActividad";
    private static final String VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES = "actividades/EventoLista";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActividadService actividadService;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private LugarRealizacionService lugarRealizacionService;

    @MockBean
    private AlquilerEspacioService alquilerService;
    
    @MockBean
    private CarritoService carritoService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private ExponenteService exponenteService;



    @WithMockUser(value = "usuario")
    @Test
    public void testListaActividad() throws Exception{
        mockMvc.perform(get("/eventos/{evento_id}/actividades",1)).andExpect(status().isOk()).
        andExpect(view().name(VIEWS_ACTIVIDAD_LISTA_ACTIVIDADES)).andExpect(model().attributeExists("actividades"));
    }

    @WithMockUser
    @Test
    public void testFormularioCrearActividad() throws Exception{

        mockMvc.perform(get("/eventos/{evento_id}/actividades/nuevo", 1)).
        andExpect(status().isOk()).andExpect(view().name(VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM)).
        andExpect(model().attributeExists("listaId","lugaresRealizacion","actividad"));
        
    }
 
   
}