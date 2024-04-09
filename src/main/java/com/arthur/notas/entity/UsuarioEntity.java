package com.arthur.notas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 100)
    private String nome;
    @Column (nullable = false, length = 100)
    private String senha;
}
