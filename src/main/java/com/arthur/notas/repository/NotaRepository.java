package com.arthur.notas.repository;

import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.entity.NotaEntity;
import com.arthur.notas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE NotaEntity nota SET" +
            " nota.titulo = :titulo," +
            " nota.conteudo = :conteudo," +
            " nota.caderno = :caderno," +
            " nota.usuario = :usuario" +
            " WHERE nota.id = :id")
    void atualizar(long id, String titulo, String conteudo, CadernoEntity caderno, UsuarioEntity usuario);

    @Query
    List<NotaEntity> findAllByUsuarioId(Long idUsuario);
}
