package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.dto.AnuncioDTO;
import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @PostMapping("/salvar")
    public ResponseEntity<AnuncioDTO> postAnuncio(@Valid @RequestBody AnuncioDTO anuncio, UriComponentsBuilder uriBuilder){
        Anuncio anuncios = anuncioService.postAnuncio(AnuncioDTO.converte(anuncio));
        URI uri = uriBuilder
                .path("/anuncio/{id}")
                .build().toUri();
        return ResponseEntity.created(uri).body(AnuncioDTO.converte(anuncios));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnuncioDTO>> listar() {
        return ResponseEntity.ok(AnuncioDTO.converte(anuncioService.listar()));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AnuncioDTO> getAnuncio(@PathVariable Long id){
        return ResponseEntity.ok(AnuncioDTO.converte(anuncioService.buscarAnuncio(id)));
    }
}