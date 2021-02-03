package org.springframework.samples.petclinic.web;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc

public class EntradaControllerTest {

    private static final int TEST_ENTRADA_ID = 1;
    private static final int TEST_CLIENTE_ID = 1;
    private static final int TEST_TIPO_ENTRADA_ID = 1;
    private static final int TEST_TIPO_ENTRADA_NUM_ENTRADAS = 10;
    private static final double TEST_TIPO_ENTRADA_PRECIO = 10.0;

    private static final NombreTiposEntrada TEST_ENTRADA_TIPO_ENTRADA = NombreTiposEntrada.DIURNA;
    public static final String VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM = "entradas/crearEntrada";

    @Autowired
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
        evento.setMedidasSanitarias("medidasSanitarias");
        evento.setFechaFin(LocalDate.of(2021, 03, 03));
        evento.setCategoria("categoria");
        evento.setFechaInicio(LocalDate.of(2021, 03, 03));
        diurna.setEvento(evento);
        entrada.setTipoEntrada(diurna);
        entrada.setCliente(luz);

        // given(this.entradas.findById(TEST_ENTRADA_ID).orElse(null)).willReturn(entrada);
        // given(this.clientes.findById(TEST_CLIENTE_ID)).willReturn(luz);
        // given(this.tiposEntradas.findById(TEST_TIPO_ENTRADA_ID).orElse(null)).willReturn(diurna);

    }

    @Test
    void testCrearEntradaForm() throws Exception {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("cliente");
        List<SimpleGrantedAuthority> aut = new ArrayList<>();
        aut.add(authority);
        UsernamePasswordAuthenticationToken auth1 = new UsernamePasswordAuthenticationToken(luzCusMo.getNombreUsuario(), luzCusMo.getPassword(), aut);
        SecurityContextHolder.getContext().setAuthentication(auth1);

        mockMvc.perform(get("/eventos/8/1/entrada")).andExpect(status().isOk())
        .andExpect(view().name(VIEWS_ENTRADA_CREATE_OR_UPDATE_FORM)).andExpect(model().attributeExists("entrada"));
    }

    @Test
    void testCrearEntradaFormExito() throws Exception {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("cliente");
        List<SimpleGrantedAuthority> aut = new ArrayList<>();
        aut.add(authority);
        UsernamePasswordAuthenticationToken auth1 = new UsernamePasswordAuthenticationToken(luzCusMo.getNombreUsuario(), luzCusMo.getPassword(), aut);
        SecurityContextHolder.getContext().setAuthentication(auth1);

        mockMvc.perform(post("/eventos/8/1/entrada").param("dni", "77778888H").param("nombreAsistente", "Raul Rodriguez Mendez")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/eventos/8"));
    }
}
