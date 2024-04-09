package com.arthur.notas.service;

import com.arthur.notas.dto.CadernoDto;
import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.repository.CadernoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadernoService {

    private final CadernoRepository repository;

    public CadernoService(CadernoRepository repository) {
        this.repository = repository;
    }

    public CadernoDto buscarId(Long id) {
        CadernoEntity caderno = repository.findById(id).orElseThrow();
        return toDto(caderno);
    }

    public List<CadernoDto> buscarTodos() {
        List<CadernoEntity> cadernos= repository.findAll();
        return cadernos.stream()
                .map(this::toDto)
                .toList();
    }
    public CadernoDto criar(CadernoDto cadernoDto) {
        CadernoEntity caderno = new CadernoEntity(cadernoDto);
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
