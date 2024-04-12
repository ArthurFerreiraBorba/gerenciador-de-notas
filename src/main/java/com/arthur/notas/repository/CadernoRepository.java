package com.arthur.notas.repository;

import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {

    @Transactional
    @Modifying
    @Query ("UPDATE CadernoEntity caderno SET" +
            " caderno.nome = :nome," +
            " caderno.usuario = :usuario" +
            " WHERE caderno.id = :id")
    void atualizar(long id,  String nome, UsuarioEntity usuario);

    @Query
    public List<CadernoEntity> findAllByUsuarioId(Long idUsuario);
}
