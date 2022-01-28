package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.services.ArmazemService;
import com.mercadolivre.projetointegradow4g1.services.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/representante")
public class RepresentanteController {

    @Autowired
    private RepresentanteService representanteService;

    @PostMapping("/salvar")
    public ResponseEntity<Representante> salvar(@Valid @RequestBody Representante representante, UriComponentsBuilder uriComponentsBuilder) {
        Representante rep = representanteService.salvar(representante);
        URI uri = uriComponentsBuilder
                .path("/representante/{id}")
                .buildAndExpand(rep.getId())
                .toUri();
        return ResponseEntity.created(uri).body(rep);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Representante>> listar() {
        return ResponseEntity.ok(representanteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Representante> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(representanteService.buscar(id));
    }
}
