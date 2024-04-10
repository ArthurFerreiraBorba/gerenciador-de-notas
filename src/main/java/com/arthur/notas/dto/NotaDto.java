package com.arthur.notas.dto;

import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.entity.UsuarioEntity;

public record NotaDto(long id, String titulo, String conteudo, CadernoEntity caderno, UsuarioEntity usuario) {
}
