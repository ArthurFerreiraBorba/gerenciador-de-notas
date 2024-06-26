package com.arthur.notas.repository;

import com.arthur.notas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    public Optional<UsuarioEntity> findByNome(String nome);
}
