package com.arthur.notas.entity;

import com.arthur.notas.dto.NotaDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notas")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (length = 100)
    private String titulo;
    @Column (length = 1000)
    private String conteudo;
    @OneToOne
    @JoinColumn(nullable = false)
    private CadernoEntity caderno;
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity usuario;

    public NotaEntity (NotaDto nota) {
        this.usuario = nota.usuario();
        this.conteudo = nota.conteudo();
        this.caderno = nota.caderno();
        this.titulo = nota.titulo();
    }
}
