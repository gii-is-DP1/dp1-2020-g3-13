package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.AutoridadesService;
import org.springframework.samples.petclinic.service.ClienteService;
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

@WebMvcTest(controllers = ClienteController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = ClienteController.class)

public class ClienteControllerTest {

    private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM = "clientes/createOrUpdateClienteForm";
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private AutoridadesService autoridadesService;

    @Test 
    @WithMockUser
    public void testFormularioRegistrarCliente() throws Exception {
        mockMvc.perform(get("/clientes/new")).andExpect(status().isOk())
        .andExpect(view().name(VIEWS_CLIENTE_CREATE_OR_UPDATE_FORM)).andExpect(model().attributeExists("cliente"));
    }
}
