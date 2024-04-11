package com.arthur.notas.repository;

import com.arthur.notas.entity.PapelEntity;
import com.arthur.notas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<PapelEntity, Long> {

    public Optional<PapelEntity> findByNome(String nome);
}
