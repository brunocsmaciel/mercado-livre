package github.macices.mercado_livre.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToOne
    private Categoria categoria;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setMae(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria() {
    }
}
