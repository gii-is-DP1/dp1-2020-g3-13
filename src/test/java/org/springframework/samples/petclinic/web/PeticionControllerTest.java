package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class PeticionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testCrearPeticionFormularioExito() throws Exception {
        mockMvc.perform(post("/peticion/new", 1).param("email", "email@email.com")
                .param("nombre_organizacion", "el nombre de la organizacion").param("cif", "12345678")
                .param("info", "informacion de la organizacion"));

    }

    // @Test
    // @WithMockUser
    // public void testFormularioCrearPeticion() throws Exception {
    // mockMvc.perform(get("/peticion/new")).andExpect(status().isOk());
    // }

}