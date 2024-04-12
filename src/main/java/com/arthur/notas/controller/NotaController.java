package com.arthur.notas.controller;

import com.arthur.notas.dto.NotaDto;
import com.arthur.notas.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("notas")
public class NotaController {

    private final NotaService service;

    public NotaController(NotaService service) {
        this.service = service;
    }

    @GetMapping ("{id}")
    public ResponseEntity<NotaDto> buscarId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.buscarId(id));
    }

    @GetMapping
    public ResponseEntity<List<NotaDto>> buscarTodos(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.status(200).body(service.buscarTodos(token.substring(7)));
    }

    @PostMapping
    public ResponseEntity<NotaDto> criar(@RequestHeader(name = "Authorization") String token, @RequestBody NotaDto nota) {
        return ResponseEntity.status(201).body(service.criar(nota, token.substring(7)));
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping ("{id}")
    public ResponseEntity<Object> atualizar(@RequestBody NotaDto nota, @PathVariable Long id) {
        service.atualizar(id, nota);
        return ResponseEntity.status(204).build();
    }
}
