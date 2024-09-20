package github.macices.mercado_livre.controller;

import github.macices.mercado_livre.dto.LoginRequest;
import github.macices.mercado_livre.dto.LoginResponse;
import github.macices.mercado_livre.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginInfo) {

        LoginResponse loginResponse = tokenService.validarLogin(loginInfo);
        return ResponseEntity.ok(loginResponse);
    }
}
