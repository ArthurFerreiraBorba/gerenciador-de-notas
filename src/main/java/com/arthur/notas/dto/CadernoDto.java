package com.arthur.notas.dto;

import com.arthur.notas.entity.UsuarioEntity;

public record CadernoDto(long id, String nome, UsuarioEntity usuario) {
}
