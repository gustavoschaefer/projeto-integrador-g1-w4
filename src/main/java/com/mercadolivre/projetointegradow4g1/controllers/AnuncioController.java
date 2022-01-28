package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.services.AnuncioService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;
    @PostMapping("/salvar")
    public ResponseEntity<Anuncio> postAnuncio(@RequestBody Anuncio anuncio, UriComponentsBuilder uriBuilder){
        anuncioService.postAnuncio(anuncio);
        URI uri = uriBuilder
                .path("/anuncio/{id}")
                .build().toUri();
        return ResponseEntity.created(uri).body(anuncio);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Anuncio>> listar() {
        return ResponseEntity.ok(anuncioService.listar());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Anuncio> getAnuncio(@PathVariable Long id){
        return ResponseEntity.ok(anuncioService.buscarAnuncio(id));
    }
}