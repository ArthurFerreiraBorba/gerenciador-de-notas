package com.arthur.notas.controller;

import com.arthur.notas.dto.CadernoDto;
import com.arthur.notas.entity.CadernoEntity;
import com.arthur.notas.service.CadernoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (name = "cadernos")
public class CadernoController {

    private final CadernoService service;

    public CadernoController(CadernoService service) {
        this.service = service;
    }

    @GetMapping ("{id}")
    public ResponseEntity<CadernoDto> buscarId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.buscarId(id));
    }

    @GetMapping
    public ResponseEntity<List<CadernoDto>> buscarTodos() {
        return ResponseEntity.status(200).body(service.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<CadernoDto> criar(@RequestBody CadernoDto caderno) {
        return ResponseEntity.status(201).body(service.criar(caderno));
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping ("{id}")
    public ResponseEntity<Object> atualizar(@RequestBody CadernoDto caderno, @PathVariable Long id) {
        service.atualizar(id, caderno);
        return ResponseEntity.status(204).build();
    }
}
