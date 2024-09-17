package github.macices.mercado_livre.dto;

import github.macices.mercado_livre.domain.Usuario;
import github.macices.mercado_livre.validation.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public class NovoUsuarioRequest {

    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    String login;

    @NotBlank @Size(min = 6)
    String senha;


    public Usuario toModel(PasswordEncoder encoder) {

        String senhaCriptografada = encoder.encode(senha);
        return new Usuario(login, senhaCriptografada);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
