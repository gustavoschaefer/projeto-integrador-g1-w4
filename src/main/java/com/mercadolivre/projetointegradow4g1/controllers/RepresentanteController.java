package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.services.RepresentanteService;

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
