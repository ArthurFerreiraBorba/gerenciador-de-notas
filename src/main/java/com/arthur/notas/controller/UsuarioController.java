package com.arthur.notas.controller;

import com.arthur.notas.dto.NotaDto;
import com.arthur.notas.dto.UsuarioDto;
import com.arthur.notas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDto> buscarId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.buscarId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodos() {
        return ResponseEntity.status(200).body(service.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(201).body(service.criar(usuarioDto));
    }
}
