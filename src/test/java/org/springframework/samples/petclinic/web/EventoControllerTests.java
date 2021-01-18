package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.model.Evento;
import org.springframework.samples.petclinic.repository.EventoRepository;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = EventoController.class)
public class EventoControllerTests {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private EventoRepository eventoRepo;
    
    @BeforeEach
    void setup(){
        Evento evento = new Evento();
        evento.setId(8);
        evento.setTipoEvento("tipoEvento");
        evento.setDescripcion("descripcion");
        evento.setNombreEvento("nombreEvento");
        evento.setMedidasSanitarias("medidasSanitarias");
        evento.setFechaFin(LocalDate.now());
        evento.setCategoria("categoria");
        evento.setFechaInicio(LocalDate.now());
        
    }

    @Test
    public void testDeberiaMostrarFormularioDeInscripccion() throws Exception{
        mockMvc.perform(get("/eventos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("evento"))
        .andExpect(view().name("eventos/editarEvento"));
    }
}
