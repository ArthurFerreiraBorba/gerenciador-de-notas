package com.arthur.notas.service;

import com.arthur.notas.dto.UsuarioDto;
import com.arthur.notas.entity.PapelEntity;
import com.arthur.notas.entity.UsuarioEntity;
import com.arthur.notas.repository.PapelRepository;
import com.arthur.notas.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PapelRepository papelRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository repository, PapelRepository papelRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.papelRepository = papelRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsuarioDto buscarId(Long id) {
        UsuarioEntity usuario = repository.findById(id).orElseThrow();
        return toDto(usuario);
    }

    public List<UsuarioDto> buscarTodos() {
        List<UsuarioEntity> usuarios = repository.findAll();
        return usuarios.stream()
                .map(this::toDto)
                .toList();
    }
    public UsuarioDto criar(UsuarioDto usuarioDto) {
        Optional<UsuarioEntity> usuarioOp = repository.findByNome(usuarioDto.nome());
        Optional<PapelEntity> papelOp = papelRepository.findByNome(usuarioDto.papel());


        if (usuarioOp.isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }
        if (papelOp.isEmpty()) {
            throw new RuntimeException("Papel não existe");
        }

        PapelEntity papel = papelOp.get();
        String senha = bCryptPasswordEncoder.encode(usuarioDto.senha());

        UsuarioEntity usuario = new UsuarioEntity(usuarioDto.nome(), senha, papel);

        repository.save(usuario);
        return toDto(usuario);
    }


    public UsuarioEntity buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseThrow();
    }


    public UsuarioDto toDto (UsuarioEntity usuario) {
        return new UsuarioDto(usuario.getNome(), usuario.getSenha(), usuario.getPapel().getNome());
    }
}
