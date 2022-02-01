package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.entities.Comprador;
import com.mercadolivre.projetointegradow4g1.services.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/compradores")
public class CompradorController {

    @Autowired
    CompradorService compradorService;

    @PostMapping("/salvar")
    public ResponseEntity<Comprador> salvar(@RequestBody Comprador comprador, UriComponentsBuilder uriBuilder){
        compradorService.salvar(comprador);
        URI uri = uriBuilder
                .path("/compradores/{id}")
                .buildAndExpand(comprador.getId())
                .toUri();
        return ResponseEntity.created(uri).body(comprador);
    }

    @GetMapping
    public ResponseEntity<List<Comprador>> listar(){
        List<Comprador> lista = compradorService.listar();
        return ResponseEntity.ok().body(lista);
    }
}
