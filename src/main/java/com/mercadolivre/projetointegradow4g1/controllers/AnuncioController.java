package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.dto.AnuncioDTO;
import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.services.AnuncioService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @PostMapping("/salvar")
    public ResponseEntity<AnuncioDTO> salvar(@Valid @RequestBody AnuncioDTO anuncio, UriComponentsBuilder uriBuilder){
        Anuncio anuncios = anuncioService.salvar(AnuncioDTO.converte(anuncio));
        URI uri = uriBuilder
                .path("/anuncio/{id}")
                .build().toUri();
        return ResponseEntity.created(uri).body(AnuncioDTO.converte(anuncios));
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDTO>> listar() {
        return ResponseEntity.ok(AnuncioDTO.converte(anuncioService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(AnuncioDTO.converte(AnuncioService.buscar(id)));
    }
}