package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.samples.petclinic.service.OrganizacionService;
import org.springframework.samples.petclinic.service.TipoEntradaService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

@WebMvcTest(controllers = EventoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = EventoController.class)

public class EventoControllerTests {

    @Autowired
    private EventoController eventoController;

    @Autowired  
    private MockMvc mockMvc;

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
    @MockBean
    private TipoEntradaService tipoEntradaService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(eventoController).isNotNull();
    }

    @BeforeEach
    void setup() {
        //inicializamos evento
        Evento evento = new Evento();
        evento.setId(8);
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        evento.setDescripcion("descripcion");
        evento.setNombreEvento("nombreEvento");
        evento.setFechaFin(LocalDate.now());
        evento.setFechaInicio(LocalDate.now());
        evento.setEsPublico(false);
        //inicializamos usuario y su autoridad
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("org1");
        usuario.setEnabled(true);
        usuario.setPassword("org1");
        Autoridades autoridades = new Autoridades();
        autoridades.setAutoridad("organizacion");
        //usuario.setAutoridades(autoridades);
        //inicializamos la organizacion
        Organizacion organizacion = new Organizacion();
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
