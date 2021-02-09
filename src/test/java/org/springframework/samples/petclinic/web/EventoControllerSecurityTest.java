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
public class EventoControllerSecurityTest {
    
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;


    private int TEST_EVENTO_ID = 1;
    private int TEST_EVENTO_ID2 = 8;
    private int TEST_EVENTO_ID3 = 9;
    @BeforeEach
    void setup() {
       mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    //LISTADO EVENTOS

    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void deberiaDevolverListadoEventosDeOrganizacion() throws Exception{
        mockMvc.perform(get("/eventos")).andExpect(status().isOk()).andExpect(view().name("eventos/listadoEventosOrganizacion"))
        .andExpect(model().attributeExists("eventos"));
    }

    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void deberiaDevolverListadoEventosDeCliente() throws Exception{
        mockMvc.perform(get("/eventos")).andExpect(status().isOk()).andExpect(view().name("eventos/listadoEventos"))
        .andExpect(model().attributeExists("eventos"));
    }

    @Test
    void deberiaDevolverListadoEventosDePersonaAnonima() throws Exception{
        mockMvc.perform(get("/eventos")).andExpect(status().isOk()).andExpect(view().name("eventos/listadoEventos"))
        .andExpect(model().attributeExists("eventos"));
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaDevolverListadoEventosDeAdmin() throws Exception{
        mockMvc.perform(get("/eventos")).andExpect(status().isOk()).andExpect(view().name("eventos/listadoEventosAdmin"))
        .andExpect(model().attributeExists("eventos"));
    }

    //DETALLES EVENTO
    @WithMockUser(username="cliente1", authorities = {"cliente"})
    @Test
    void deberiaMostrarDetallesEventosDeCliente() throws Exception{
        mockMvc.perform(get("/eventos/1")).andExpect(status().isOk()).andExpect(view().name("eventos/detallesEventoCliente"))
        .andExpect(model().attributeExists("evento"));
    }
    @WithMockUser(username="organizacion1", authorities = {"organizacion"})
    @Test
    void deberiaMostrarDetallesEventosDeOrganizacion() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}", TEST_EVENTO_ID)).andExpect(status().isOk()).andExpect(view().name("eventos/detallesEvento"))
        .andExpect(model().attributeExists("evento"));
    }
    @WithMockUser(username="alebangon", authorities = {"admin"})
    @Test
    void deberiaMostrarDetallesEventosDeAdmin() throws Exception {
        mockMvc.perform(get("/eventos/{eventoId}", TEST_EVENTO_ID)).andExpect(status().isOk()).andExpect(view().name("eventos/detallesEvento"))
        .andExpect(model().attributeExists("evento"));
    }

    //AÑADIR A FAVORITOS
    @WithMockUser(username="cliente1", authorities={"cliente"})
    @Test
    void deberiaAñadirAFavoritosCliente() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/anadirEventosFavoritos", TEST_EVENTO_ID)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos"));
    }
    @WithMockUser(username="organizacion1", authorities={"organizacion"})
    @Test
    void noDeberiaAñadirAFavoritosOrg() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/anadirEventosFavoritos", TEST_EVENTO_ID)).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="alebangon", authorities={"admin"})
    @Test
    void noDeberiaAñadirAFavoritosAdmin() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/anadirEventosFavoritos", TEST_EVENTO_ID)).andExpect(status().is4xxClientError());
    }

    //HACER PUBLICO 
    @WithMockUser(username="cliente1", authorities={"cliente"})
    @Test
    void noDeberiaHacerPublicoCliente() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/hacerPublico", TEST_EVENTO_ID)).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="organizacion1", authorities={"organizacion"})
    @Test
    void deberiaHacerPublicoOrg() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/hacerPublico", TEST_EVENTO_ID)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos/{eventosId}"));
    }
    @WithMockUser(username="alebangon", authorities={"admin"})
    @Test
    void noDeberiaHacerPublicoAdmin() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/hacerPublico", TEST_EVENTO_ID)).andExpect(status().is4xxClientError());
    }
    //NUEVO EVENTO
    @WithMockUser(username="cliente1", authorities={"cliente"})
    @Test
    void noDeberiaAñadirEventoCliente() throws Exception{
        mockMvc.perform(get("/eventos/nuevo")).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="organizacion1", authorities={"organizacion"})
    @Test
    void deberiaAñadirEventoOrg() throws Exception{
        mockMvc.perform(post("/eventos/nuevo").param("nombreEvento", "evento").param("tipoEvento", "SOCIALES")
        .param("descripcion", "Descripcion de mas de quince caracteres").param("fechaInicio", "2022/12/12").param("fechaFin", "2022/12/12")
        .param("categoria", "eventos sociales").param("medidasSanitarias", "Medidas sanitarias para el evento").with(csrf())).andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/eventos"));
    }

    //BORRAR EVENTO
    @WithMockUser(username="cliente1", authorities={"cliente"})
    @Test
    void noDeberiaBorrarCliente() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/delete", TEST_EVENTO_ID)).andExpect(status().is4xxClientError());
    }
    @WithMockUser(username="organizacion1", authorities={"organizacion"})
    @Test
    void noDeberiaBorrarOrg() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/delete", TEST_EVENTO_ID)).andExpect(status().isOk()).andExpect(view().name("eventos/organizacionSinPermiso"));
    }
    @WithMockUser(username="organizacion1", authorities={"organizacion"})
    @Test
    void deberiaBorrarOrg() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/delete", TEST_EVENTO_ID3)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos"));
    }
    @WithMockUser(username="alebangon", authorities={"admin"})
    @Test
    void DeberiaBorrarAdmin() throws Exception{
        mockMvc.perform(get("/eventos/{eventoId}/delete", TEST_EVENTO_ID2)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos"));
    }
}
