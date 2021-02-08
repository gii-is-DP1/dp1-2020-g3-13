package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.service.CarritoService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EntradaService;
import org.springframework.samples.petclinic.service.EventoService;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class LugarRealizacionControllerSecurityTest {
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    void setup() {
       mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    //LISTADO LUGARES
    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void noDeberiaDevolverListadoLugaresDeOrganizacion() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void noDeberiaDevolverListadoLugaresDeCliente() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion")).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaDevolverListadoLugaresAdmin() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion")).andExpect(status().isOk()).andExpect(view().name("/lugaresRealizacion/listadoLugaresRealizacion"))
        .andExpect(model().attributeExists("lugaresRealizacion"));
    }
    //LISTADO LUGARES
    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void noDeberiaDevolverDetallesLugaresDeOrganizacion() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/1")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void noDeberiaDevolverDetallesLugaresDeCliente() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/1")).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaDevolverDetallesLugaresAdmin() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/1")).andExpect(status().isOk()).andExpect(view().name("lugaresRealizacion/detallesLugarRealizacion"))
        .andExpect(model().attributeExists("lugarRealizacion"));
    }
    //CREAR LUGAR
    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void noDeberiaCrearLugarOrganizacion() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/nuevo")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void noDeberiaCrearLugarCliente() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/nuevo")).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaCrearLugarAdmin() throws Exception{
        mockMvc.perform(post("/lugaresRealizacion/nuevo").param("nombre_recinto", "Estadio Benito Villamarín").param("aforo", "30000")
        .param("direccion", "Av de Heliópolis, s/n Sevilla").param("caracteristicas", "Muy grande, con capacidad para muchas personas")
        .param("telefono", "695969421").param("email", "correo@gmail.com").param("precio", "10.0").param("url_foto", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Estadio_Benito_Villamar%C3%ADn_2018001.jpg/413px-Estadio_Benito_Villamar%C3%ADn_2018001.jpg").with(csrf()))
        .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/lugaresRealizacion/"));
    }

    //CREAR LUGAR
    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void noDeberiaEditarLugarOrganizacion() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/1/editar")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void noDeberiaEditarLugarCliente() throws Exception{
        mockMvc.perform(get("/lugaresRealizacion/1/editar")).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaEditarLugarAdmin() throws Exception{
        mockMvc.perform(post("/lugaresRealizacion/1/editar").param("nombre_recinto", "Estadio Benito Villamarín").param("aforo", "30000")
        .param("direccion", "Av de Heliópolis, s/n Sevilla").param("caracteristicas", "Muy grande, con capacidad para muchas personas")
        .param("telefono", "695969421").param("email", "correo@gmail.com").param("precio", "10.0").param("url_foto", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Estadio_Benito_Villamar%C3%ADn_2018001.jpg/413px-Estadio_Benito_Villamar%C3%ADn_2018001.jpg").with(csrf()))
        .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/lugaresRealizacion/"));
    }


}
