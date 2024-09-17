package github.macices.mercado_livre.dto;

import github.macices.mercado_livre.domain.Categoria;
import github.macices.mercado_livre.validation.ExistsId;
import github.macices.mercado_livre.validation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class NovaCategoriaRequest {

    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public Categoria toDomain(EntityManager entityManager) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = entityManager.find(Categoria.class, idCategoriaMae);
            validaCategoria(categoriaMae);
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }

    private void validaCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new EntityNotFoundException();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}
