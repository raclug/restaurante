package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void deveCriarTipoUsuario() throws Exception {
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        dto.setNome("Administrador");

        mockMvc.perform(post("/v1/tipos-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Administrador")));
    }

    @Test
    @Order(2)
    void deveListarTiposUsuario() throws Exception {

        mockMvc.perform(get("/v1/tipos-usuario?pagina=0&tamanhoPagina=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    @Order(3)
    void deveConsultarTipoUsuarioPorId() throws Exception {

        mockMvc.perform(get("/v1/tipos-usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Administrador")));
    }

    @Test
    @Order(4)
    void deveAlterarTipoUsuario() throws Exception {
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        dto.setNome("Chef");

        mockMvc.perform(put("/v1/tipos-usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Chef")));
    }

    @Test
    @Order(5)
    void deveRemoverTipoUsuario() throws Exception {
        mockMvc.perform(delete("/v1/tipos-usuario/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/v1/tipos-usuario/1"))
                .andExpect(status().is4xxClientError());
    }

}