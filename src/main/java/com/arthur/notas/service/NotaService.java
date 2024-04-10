package com.arthur.notas.service;

import com.arthur.notas.dto.NotaDto;
import com.arthur.notas.entity.NotaEntity;
import com.arthur.notas.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository repository;

    public NotaService(NotaRepository repository) {
        this.repository = repository;
    }

    public NotaDto buscarId(Long id) {
        NotaEntity nota = repository.findById(id).orElseThrow();
        return toDto(nota);
    }

    public List<NotaDto> buscarTodos() {
        List<NotaEntity> notas= repository.findAll();
        return notas.stream()
                .map(this::toDto)
                .toList();
    }
    public NotaDto criar(NotaDto notaDto) {
        NotaEntity nota = new NotaEntity(notaDto);
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
