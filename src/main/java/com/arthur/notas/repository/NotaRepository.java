package com.arthur.notas.repository;

import com.arthur.notas.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
}
