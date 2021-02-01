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
import org.springframework.samples.petclinic.model.Actividad;
import org.springframework.samples.petclinic.model.Autoridades;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.samples.petclinic.repository.AutoridadesRepository;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.samples.petclinic.service.EventoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc


public class ClienteControllerTest {
    @Autowired
    private ClienteController clienteController;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    @Autowired
    private AuthenticationManager authManager;
    private Cliente cliente;
    private Autoridades autoridades;
    private Usuario usuario;

    @Test
    public void contextLoads() throws Exception {
        assertThat(clienteController).isNotNull();
    }
    @BeforeEach
    void setup() {

        //inicializamos usuario y su autoridad
        usuario = new Usuario();
        usuario.setNombreUsuario("clientetest");
        usuario.setEnabled(true);
        usuario.setPassword("clientetest");
        autoridades = new Autoridades();
        autoridades.setAutoridad("cliente");
        usuario.setAutoridades(autoridades);
        //inicializamos cliente 
        cliente= new Cliente();
        cliente.setApellidos("tessito");
        cliente.setNombre("clientetest");
        cliente.setEntradas(null);
        cliente.setEventosFavoritos(null);
        cliente.setTelefono("690321616");
    }
    private static final String VIEWS_CONSULTA_CREATE_OR_UPDATE_FORM = "clientes/new";
    @Test
    public void testDeberiaMostrarFormularioDeRegistro() throws Exception{
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("cliente");
        List<SimpleGrantedAuthority> aut = new ArrayList<>();
        aut.add(authority);
        UsernamePasswordAuthenticationToken auth1 = new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), usuario.getPassword(), aut);
        SecurityContextHolder.getContext().setAuthentication(auth1);
      
        this.mockMvc.perform(get("/clientes/new"));
        this.mockMvc.perform(get("/clientes/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
        .andExpect(view().name("/login"));
}
}