package github.macices.mercado_livre.domain;

import github.macices.mercado_livre.dto.LoginRequest;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

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

    public Set<Role> getRoles() {
        return roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = LocalDateTime.now();
    }

    /*
    * Construtor não deve ser utilizado como vazio, foi necessário pois o JPA pede o construtor default
    * */
    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isLoginValido(LoginRequest loginRequest, BCryptPasswordEncoder encoder) {
        return encoder.matches(loginRequest.getSenha(), this.senha);
    }
}
