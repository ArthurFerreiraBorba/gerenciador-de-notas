package com.arthur.notas.service;

import com.arthur.notas.dto.CadernoDto;
import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.entity.UsuarioEntity;
import com.arthur.notas.repository.CadernoRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadernoService {

    private final CadernoRepository repository;
    private final JwtDecoder jwtDecoder;
    private final UsuarioService usuarioService;

    public CadernoService(CadernoRepository repository, JwtDecoder jwtDecoder, UsuarioService usuarioService) {
        this.repository = repository;
        this.jwtDecoder = jwtDecoder;
        this.usuarioService = usuarioService;
    }

    public CadernoDto buscarId(Long id) {
        CadernoEntity caderno = repository.findById(id).orElseThrow();
        return toDto(caderno);
    }

    public List<CadernoDto> buscarTodos(String token) {

        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        List<CadernoEntity> cadernos = repository.findAllByUsuarioId(idUsuario);

        return cadernos.stream()
                .map(this::toDto)
                .toList();
    }
    public CadernoDto criar(CadernoDto cadernoDto, String token) {

        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        UsuarioEntity usuario = usuarioService.buscarId(idUsuario);

        CadernoEntity caderno = new CadernoEntity(cadernoDto);
        caderno.setUsuario(usuario);
        repository.save(caderno);
        return toDto(caderno);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public void atualizar(Long id, CadernoDto cadernoDto) {
        repository.atualizar(id, cadernoDto.nome(), cadernoDto.usuario());
    }

    public CadernoDto toDto (CadernoEntity caderno) {
        return new CadernoDto(caderno.getId(), caderno.getNome(), caderno.getUsuario());
    }
}
