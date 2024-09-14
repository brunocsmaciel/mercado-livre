package github.macices.mercado_livre.dto;

import github.macices.mercado_livre.Usuario;
import github.macices.mercado_livre.validation.EmailUnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record NovoUsuarioRequest(@EmailUnico String login,
                                 @NotBlank @Size(min = 6) String senha) {

    public Usuario toModel(PasswordEncoder encoder) {

        String senhaCriptografada = encoder.encode(senha);
        return new Usuario(login, senhaCriptografada);
    }
}
