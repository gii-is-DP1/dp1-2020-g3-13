package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Entrada;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.NombreTiposEntrada;
import org.springframework.samples.petclinic.model.TipoEntrada;
import org.springframework.samples.petclinic.model.TipoEvento;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.repository.EntradaRepository;
import org.springframework.samples.petclinic.repository.TipoEntradaRepository;
import org.springframework.security.authentication.AuthenticationManager;
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

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ExtendWith(SpringExtension.class)
public class EntradaControllerTest {

    private static final int TEST_ENTRADA_ID = 1;
    private static final int TEST_CLIENTE_ID = 1;
    private static final int TEST_TIPO_ENTRADA_ID = 1;
    private static final int TEST_TIPO_ENTRADA_NUM_ENTRADAS = 10;
    private static final double TEST_TIPO_ENTRADA_PRECIO = 10.0;

    private static final NombreTiposEntrada TEST_ENTRADA_TIPO_ENTRADA = NombreTiposEntrada.DIURNA;
    public static final String VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM = "entradas/crearEntrada";


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    @MockBean
    @Autowired
    private EntradaRepository entradas;
    @MockBean
    @Autowired
    private ClienteRepository clientes;
    @MockBean
    @Autowired
    private TipoEntradaRepository tiposEntradas;

    @MockBean
    @Autowired
    private AuthenticationManager authManager;

    private Entrada entrada;
    private Evento evento;
    private TipoEntrada diurna;
    private Cliente luz;
    private Usuario luzCusMo;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
        entrada = new Entrada();
        entrada.setId(TEST_ENTRADA_ID);
        entrada.setDni("77777777A");
        entrada.setNombreAsistente("Luz Cuesta Mogollon");
        luz = new Cliente();
        diurna = new TipoEntrada();
        luzCusMo = new Usuario();
        // definimos el cliente
        luz.setId(TEST_CLIENTE_ID);
        luz.setNombre("Luz");
        luz.setApellidos("Cuesta Mogollon");
        luz.setEmail("luzCusMo@protomail.com");
        luz.setTelefono("788788744");
        // definimos el usuario
        luzCusMo.setNombreUsuario("luzCusMo");
        luzCusMo.setEnabled(true);
        luzCusMo.setPassword("MogoCuesta123");
        Autoridades aut = new Autoridades();
        aut.setAutoridad("cliente");
        luzCusMo.setAutoridades(aut);
        luz.setUsuario(luzCusMo);
        // definimos el tipoEntrada
        diurna.setFechaFin(LocalDateTime.of(2021, 03, 03, 13, 00));
        diurna.setFechaInicio(LocalDateTime.of(2021, 03, 03, 10, 00));
        diurna.setId(TEST_TIPO_ENTRADA_ID);
        diurna.setNombre(TEST_ENTRADA_TIPO_ENTRADA);
        diurna.setNumEntradas(TEST_TIPO_ENTRADA_NUM_ENTRADAS);
        diurna.setPrecio(TEST_TIPO_ENTRADA_PRECIO);
        // definimos el evento
        evento = new Evento();
        evento.setId(8);
        evento.setTipoEvento(TipoEvento.ACADEMICOS);
        evento.setDescripcion("descripcion");
        evento.setNombreEvento("nombreEvento");
        evento.setFechaFin(LocalDate.of(2021, 03, 03));
        evento.setFechaInicio(LocalDate.of(2021, 03, 03));
        diurna.setEvento(evento);
        entrada.setTipoEntrada(diurna);
        entrada.setCliente(luz);

    }
    @WithMockUser(username = "orgRandom", authorities = {"organizacion"})
    @Test
    void noPuedeCrearEntradaForm() throws Exception {
        mockMvc.perform(get("/eventos/8/1/entrada")).andExpect(status().isForbidden());
    }
    @WithMockUser(username = "clienteRandom", authorities = {"cliente"})
    @Test
    void puedeCrearEntradaForm() throws Exception {
        mockMvc.perform(get("/eventos/8/1/entrada")).andExpect(status().isOk())
        .andExpect(view().name(VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM)).andExpect(model().attributeExists("entrada"));
    }

    @WithMockUser(username = "clienteRandom", authorities = {"cliente"})
    @Test
    void testCrearEntradaFormExito() throws Exception {
        
        mockMvc.perform(post("/eventos/1/2/entrada").param("dni", "77934193G")
        .param("nombreAsistente", "Raul Rodriguez Mendez").with(csrf()))
        .andExpect(status().isOk());
    }
}
