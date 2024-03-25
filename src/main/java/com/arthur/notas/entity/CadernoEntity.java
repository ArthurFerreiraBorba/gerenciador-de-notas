package com.arthur.notas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "cadernos")
public class CadernoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (length = 100)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity usuario;
}
