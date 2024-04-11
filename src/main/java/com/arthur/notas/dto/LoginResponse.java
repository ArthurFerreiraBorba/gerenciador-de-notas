package com.arthur.notas.dto;

public record LoginResponse(String token, long tempoExpiracao) {
}
