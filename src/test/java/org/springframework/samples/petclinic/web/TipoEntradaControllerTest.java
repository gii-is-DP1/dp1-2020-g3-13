package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.SponsorService;
import org.springframework.samples.petclinic.service.TipoEntradaService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@WebMvcTest(controllers = TipoEntradaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = TipoEntradaController.class)

public class TipoEntradaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SponsorService sponsorService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private TipoEntradaService tipoEntradaService;

    @MockBean
    private EventoRepository eventoRepository;

    // @Test
    // @WithMockUser
    // public void testAnadirTipoEntradaAEvento() throws Exception {
    // mockMvc.perform(
    // post("/eventos/{evento_id}/tipoEntradas/nuevo", 1).param("nombre",
    // "DIURNA").param("numEntradas", "10")
    // .param("fechaInicio", "2021/02/11 10:57").param("fechaFin", "2021/02/11
    // 11:00")
    // .param("precio", "5.0").param("actividades",
    // "actividades:[12,13]").with(csrf()))
    // .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos/{evento_id}"));

    // }

}