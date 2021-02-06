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
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.samples.petclinic.model.TipoEvento;
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

//@WebMvcTest(value = EventoController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EventoControllerTests {

    @Autowired
    private EventoController eventoController;

    @Autowired  
    private MockMvc mockMvc;

    @Autowired
    private AutoridadesRepository autoridadesRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    @Autowired
    private AuthenticationManager authManager;

    private Evento evento;

    private Usuario usuario;

    private Autoridades autoridades;
    
    private Organizacion organizacion;

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

    @Test
    public void testDeberiaMostrarFormularioDeInscripccion() throws Exception{
        //Log in
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("organizacion");
        List<SimpleGrantedAuthority> aut = new ArrayList<>();
        aut.add(authority);
        UsernamePasswordAuthenticationToken auth1 = new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), usuario.getPassword(), aut);
        SecurityContextHolder.getContext().setAuthentication(auth1);
        
        //acción
        this.mockMvc.perform(get("/eventos/nuevo")).andExpect(status().isOk()).andExpect(model().attributeExists("evento"))
        .andExpect(view().name("eventos/editarEvento"));
    }

    @Test
    public void testDeberiaCrearEvento() throws Exception{
        //Log in
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("organizacion");
        List<SimpleGrantedAuthority> aut = new ArrayList<>();
        aut.add(authority);
        UsernamePasswordAuthenticationToken auth1 = new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), usuario.getPassword(), aut);
        SecurityContextHolder.getContext().setAuthentication(auth1);
        long cant = eventoRepository.count();
        this.mockMvc.perform(get("/eventos/nuevo"));

        this.mockMvc.perform(post("/eventos/nuevo").param("tipoEvento", "tipo evento").param("descripccion","descripccion")
            .param("nombreEvento", "nombreEvento").param("medidasSanitarias", "medidasSanitarias").param("fechaFin", "2020/12/12")
            .param("categoria", "categoria").param("fechaInicio", "2020/12/1").with(csrf())).andExpect(model().attributeExists("evento")).andExpect(status().isOk());

        }
}
