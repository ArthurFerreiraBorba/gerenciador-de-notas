package com.arthur.notas.controller;

import com.arthur.notas.dto.LoginRequest;
import com.arthur.notas.dto.LoginResponse;
import com.arthur.notas.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tokens")
public class TokenController {

    private final TokenService service;

    public TokenController(TokenService tokenService) {
        this.service = tokenService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> gerarToken(@RequestBody LoginRequest loginRequest) {
        LoginResponse login = service.gerarToken(loginRequest);

        return ResponseEntity.ok(login);
    }
}
