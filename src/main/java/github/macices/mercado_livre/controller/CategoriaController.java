package github.macices.mercado_livre.controller;

import github.macices.mercado_livre.domain.Categoria;
import github.macices.mercado_livre.dto.NovaCategoriaRequest;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastraCategoria(@RequestBody @Valid NovaCategoriaRequest request) {

        Categoria categoria = request.toDomain(entityManager);
        entityManager.persist(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Categoria criada");
    }
}
