package com.arthur.notas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "papeis")
public class PapelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 100)
    private String nome;
}
