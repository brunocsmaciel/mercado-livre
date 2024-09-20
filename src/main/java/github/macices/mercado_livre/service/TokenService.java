package github.macices.mercado_livre.service;

import github.macices.mercado_livre.domain.Usuario;
import github.macices.mercado_livre.dto.LoginRequest;
import github.macices.mercado_livre.dto.LoginResponse;
import github.macices.mercado_livre.domain.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final EntityManager manager;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public TokenService(EntityManager manager,
                        BCryptPasswordEncoder passwordEncoder,
                        JwtEncoder jwtEncoder) {
        this.manager = manager;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse validarLogin(LoginRequest loginRequest) {

        Query query = manager.createQuery("select u from Usuario u where u.login = :email")
                .setParameter("email", loginRequest.getUsuario());

        List<Usuario> usuarios = query.getResultList();
        Usuario usuario = usuarios.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("usuário não encontrado"));

        if (!usuario.isLoginValido(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("usuário ou senha inválidos");
        }

        var agora = Instant.now();
        var expiraEm = agora.plusSeconds(300L);

        var scopes = usuario.getRoles()
                .stream()
                .map(Role::getNome)
                .collect(Collectors.toList());

        if (scopes.isEmpty()) {
            scopes.add("SCOPE_BASIC");
        }
        var claims = JwtClaimsSet.builder().issuer("mybackend")
                .subject(usuario.getId().toString())
                .issuedAt(agora)
                .expiresAt(expiraEm)
                .claim("scope", scopes)
                .build();

        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwt, expiraEm.getLong(ChronoField.INSTANT_SECONDS));
    }
}
