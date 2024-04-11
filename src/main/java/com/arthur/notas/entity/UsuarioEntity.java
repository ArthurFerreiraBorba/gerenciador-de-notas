package com.arthur.notas.entity;

import com.arthur.notas.dto.UsuarioDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 100, unique = true)
    private String nome;
    @Column (nullable = false, length = 100)
    private String senha;
    @ManyToOne
    private PapelEntity papel;

    public UsuarioEntity () {
    }

    public UsuarioEntity (String nome, String senha, PapelEntity papel) {
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }
}
