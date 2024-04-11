package com.arthur.notas.service;

import com.arthur.notas.dto.LoginRequest;
import com.arthur.notas.dto.LoginResponse;
import com.arthur.notas.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final UsuarioService usuarioService;

    private static long TEMPO_EXPIRACAO = 36000L;

    public TokenService(BCryptPasswordEncoder bCryptEncoder, JwtEncoder jwtEncoder, UsuarioService usuarioService) {
        this.bCryptEncoder = bCryptEncoder;
        this.jwtEncoder = jwtEncoder;
        this.usuarioService = usuarioService;
    }

    public boolean validarSenha(LoginRequest loginRequest, UsuarioEntity usuario) {
        return bCryptEncoder.matches(loginRequest.senha(), usuario.getSenha());
    }

    public LoginResponse gerarToken(LoginRequest loginRequest) {

        UsuarioEntity usuario = usuarioService.buscarPorNome(loginRequest.nome());

        if (!validarSenha(loginRequest, usuario)) {
            throw new RuntimeException("senha invalida");
        }

        Instant now = Instant.now();

        String scope = usuario.getPapel().getNome();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("gerenciados-notas")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO))
                .subject(usuario.getId().toString())
                .claim("scope", scope)
                .build();

        var valorJWT = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO);
    }
}
