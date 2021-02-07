package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.SponsorService;
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

@WebMvcTest(controllers = SponsorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = SponsorController.class)

public class SponsorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SponsorService sponsorService;

    @MockBean
    private OrganizacionService organizacionService;

    @MockBean
    private EventoService eventoService;

    @Test
    @WithMockUser
    public void testAnadirSponsorAEvento() throws Exception {
        mockMvc.perform(post("/eventos/{evento_id}/sponsors/nuevo", 1).param("empresa", "Empresa de Prueba")
                .param("urlWeb", "https://urlweb").param("urlLogo", "https://urlLogo").with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos/{evento_id}"));

    }

    // @Test
    // @DisplayName("testAnadirSponsorAEventoFail")
    // public void testAnadirSponsorAEventoFail() throws Exception {
    // mockMvc.perform(post("/eventos/{evento_id}/sponsors/nuevo",
    // 1).param("empresa", "Empresa de Prueba")
    // .param("urlWeb", "https://urlweb").param("urlLogo",
    // "https://urlLogo").with(csrf()))
    // .andExpect(status().isForbidden());
    // }

}