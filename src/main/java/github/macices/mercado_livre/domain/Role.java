package github.macices.mercado_livre.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "role_name")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setRoleId(Long roleId) {
        this.id = roleId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public enum Values {
        ADMIN(1L),
        BASIC(2L);

        Values(long roleId) {
            this.roleId = roleId;
        }

        long roleId;

        public long getRoleId() {
            return roleId;
        }
    }
}
