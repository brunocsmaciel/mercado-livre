package github.macices.mercado_livre;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuarioControllerTest {


    MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Deve cadastrar usuario com dados validos com sucesso. Usuario com cadastro de email já existente deve ser recusado")
    void deveCadastrarUsuarioComDaddosValidos() throws Exception {

        String usuario = TestUtils.lerArquivo("/payloads/usuario-valido.json");

        MockHttpServletResponse response = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuario))
                .andReturn().getResponse();

        int statusCriado = 201;
        assertEquals(statusCriado, response.getStatus());

        response = mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuario))
                .andReturn().getResponse();

        int statusEmailJaCadastrado = 400;
        assertEquals(statusEmailJaCadastrado, response.getStatus());
    }


    @Test
    void deveRecusarCadastroUsuarioComDadosInvalidos() throws Exception {

        String usuario = TestUtils.lerArquivo("/payloads/usuario-invalido.json");

        MockHttpServletResponse response = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuario))
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());

        int statusEmailJaCadastrado = 400;
        assertEquals(statusEmailJaCadastrado, response.getStatus());
    }
}