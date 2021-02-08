package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AutoridadesRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest(controllers = EventoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = EventoController.class)

public class EventoControllerTests {

    @Autowired
    private EventoController eventoController;

    @Autowired  
    private MockMvc mockMvc;

    private Evento evento;

    private Usuario usuario;

    private Autoridades autoridades;
    
    private Organizacion organizacion;

    @MockBean
    private EventoService eventoService;
    @MockBean
    private EventoRepository eventoRepo;

    @MockBean
    private OrganizacionService organizacionService;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private AdminService adminService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(eventoController).isNotNull();
    }

    @BeforeEach
    void setup() {
        //inicializamos evento
        evento = new Evento();
        evento.setId(8);
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        evento.setDescripcion("descripcion");
        evento.setNombreEvento("nombreEvento");
        evento.setMedidasSanitarias("medidasSanitarias");
        evento.setFechaFin(LocalDate.now());
        evento.setCategoria("categoria");
        evento.setFechaInicio(LocalDate.now());
        evento.setEsPublico(false);
        //inicializamos usuario y su autoridad
        usuario = new Usuario();
        usuario.setNombreUsuario("org1");
        usuario.setEnabled(true);
        usuario.setPassword("org1");
        autoridades = new Autoridades();
        autoridades.setAutoridad("organizacion");
        usuario.setAutoridades(autoridades);
        //inicializamos la organizacion
        organizacion = new Organizacion();
        organizacion.setUsuario(usuario);
        organizacion.setCif("18467868486");
        organizacion.setEmail("org1@gmail.com");
        organizacion.setInfo("info de la organizacion");
        organizacion.setNombreOrganizacion("org1");
        evento.setOrganizacion(organizacion);

    }
    @WithMockUser
    @Test
    public void testDeberiaMostrarFormularioDeInscripccion() throws Exception{
        mockMvc.perform(get("/eventos/nuevo")).andExpect(status().isOk()).andExpect(model().attributeExists("evento"))
        .andExpect(view().name("eventos/editarEvento"));

    }

    // @Test
    // public void testDeberiaMostrarDetallesEvento() throws Exception{
    //     mockMvc.perform(get("/eventos")).andExpect(status().isOk()).andExpect(model().attributeExists("evento"))
    //     .andExpect(view().name("eventos/listadoEventos"));
    // }
}
