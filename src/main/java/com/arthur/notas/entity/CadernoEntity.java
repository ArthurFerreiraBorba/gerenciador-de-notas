package com.arthur.notas.entity;

import com.arthur.notas.dto.CadernoDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "cadernos")
public class CadernoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 100)
    private String nome;
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity usuario;

    public CadernoEntity() {}

    public CadernoEntity(CadernoDto cadernoDto) {
        this.nome = cadernoDto.nome();
        this.usuario = cadernoDto.usuario();
    }
}
