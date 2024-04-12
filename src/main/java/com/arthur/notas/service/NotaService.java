package com.arthur.notas.service;

import com.arthur.notas.dto.NotaDto;
import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.entity.NotaEntity;
import com.arthur.notas.entity.UsuarioEntity;
import com.arthur.notas.repository.NotaRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository repository;
    private final JwtDecoder jwtDecoder;
    private final UsuarioService usuarioService;

    public NotaService(NotaRepository repository, JwtDecoder jwtDecoder, UsuarioService usuarioService) {
        this.repository = repository;
        this.jwtDecoder = jwtDecoder;
        this.usuarioService = usuarioService;
    }

    public NotaDto buscarId(Long id) {
        NotaEntity nota = repository.findById(id).orElseThrow();
        return toDto(nota);
    }

    public List<NotaDto> buscarTodos(String token) {

        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        List<NotaEntity> notas = repository.findAllByUsuarioId(idUsuario);

        return notas.stream()
                .map(this::toDto)
                .toList();
    }
    public NotaDto criar(NotaDto notaDto, String token) {

        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        UsuarioEntity usuario = usuarioService.buscarId(idUsuario);

        NotaEntity nota = new NotaEntity(notaDto);
        nota.setUsuario(usuario);
        repository.save(nota);
        return toDto(nota);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public void atualizar(Long id, NotaDto notaDto) {
        repository.atualizar(id, notaDto.titulo(), notaDto.conteudo(),notaDto.caderno(),notaDto.usuario());
    }

    public NotaDto toDto (NotaEntity nota) {
        return new NotaDto(nota.getId(), nota.getTitulo(), nota.getConteudo(), nota.getCaderno(), nota.getUsuario());
    }
}
