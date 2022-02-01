package com.mercadolivre.projetointegradow4g1.controllers;


import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("/salvar")
    public ResponseEntity<Carrinho> salvar(@RequestBody Carrinho carrinho, UriComponentsBuilder uriBuilder){
        carrinhoService.salvar(carrinho);
        URI uri = uriBuilder
                .path("/carrinhos/{id}")
                .buildAndExpand(carrinho.getId())
                .toUri();
        return ResponseEntity.created(uri).body(carrinho);
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> listar(){
        List<Carrinho> lista = carrinhoService.listar();
        return ResponseEntity.ok().body(lista);
    }


}
