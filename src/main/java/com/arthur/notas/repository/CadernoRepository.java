package com.arthur.notas.repository;

import com.arthur.notas.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
}
