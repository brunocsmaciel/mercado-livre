package github.macices.mercado_livre.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "instante_cadastro")
    private LocalDateTime instanteCadastro;


    public Usuario(String login, String senha) {

        // Principios de programação defensiva
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }
}
