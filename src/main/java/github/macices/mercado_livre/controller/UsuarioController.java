package github.macices.mercado_livre.controller;

import github.macices.mercado_livre.domain.Usuario;
import github.macices.mercado_livre.dto.NovoUsuarioRequest;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final EntityManager entityManager;

    //1
    private final PasswordEncoder encoder;

    public UsuarioController(EntityManager entityManager, PasswordEncoder encoder) {
        this.entityManager = entityManager;
        this.encoder = encoder;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> novoUsuario(@RequestBody @Valid NovoUsuarioRequest request) {

        //1
        Usuario usuario = request.toModel(encoder);
        entityManager.persist(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Usuário de id %s criado", usuario.getId()));
    }
}
