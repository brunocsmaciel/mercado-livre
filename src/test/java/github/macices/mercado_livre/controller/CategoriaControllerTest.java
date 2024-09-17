package github.macices.mercado_livre.controller;

import github.macices.mercado_livre.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoriaControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Deve cadastrar categoria com dados validos com sucesso. Categorias com nome já existente devem ser recusadas")
    void deveCadastrarCategoriasValidas() throws Exception {

        String categoria = TestUtils.lerArquivo("/payloads/categoria-valida.json");

        MockHttpServletResponse response = mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoria))
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());
        int statusCriado = 201;
        assertEquals(statusCriado, response.getStatus());

        response = mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoria))
                .andReturn().getResponse();

        int statusCategoriaNomeJaCadastrado = 400;
        assertEquals(statusCategoriaNomeJaCadastrado, response.getStatus());
    }
}
