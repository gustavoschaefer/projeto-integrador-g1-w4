package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.dto.ArmazemDTO;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.services.ArmazemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/armazem")
public class ArmazemController {

    @Autowired
    private ArmazemService armazemService;

    @PostMapping("/salvar")
    public ResponseEntity<ArmazemDTO> salvar(@Valid @RequestBody ArmazemDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Armazem arm = armazemService.salvar(ArmazemDTO.converte(dto));
        URI uri = uriComponentsBuilder
                .path("/armazem/{id}")
                .buildAndExpand(arm.getId())
                .toUri();
        return ResponseEntity.created(uri).body(ArmazemDTO.converte(arm));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ArmazemDTO>> listar() {
        return ResponseEntity.ok(ArmazemDTO.converte(armazemService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmazemDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ArmazemDTO.converte(armazemService.buscar(id)));
    }
}