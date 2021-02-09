package org.springframework.samples.petclinic.web;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.LugarRealizacionService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = LugarRealizacionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = LugarRealizacionController.class)
public class LugarRealizacionControllerTest {

    @Autowired
    private LugarRealizacionController lugarRealizacionController;

    @Autowired  
    private MockMvc mockMvc;
    @Autowired  
    @MockBean
	private LugarRealizacionService lugarRealizacionService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(lugarRealizacionController).isNotNull();
    }
    @WithMockUser
    @Test
    public void testDeberiaMostrarListadoDeLugares() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion")).andExpect(status().isOk()).andExpect(model().attributeExists("lugaresRealizacion"))
        .andExpect(view().name("/lugaresRealizacion/listadoLugaresRealizacion"));

    }
    @WithMockUser
    @Test
    public void testDeberiaMostrarDetallesDelLugar() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/{lugarRealizacionId}",2)).andExpect(status().isOk()).andExpect(model().attributeExists("lugarRealizacion"))
        .andExpect(view().name("lugaresRealizacion/detallesLugarRealizacion"));

    }
}
