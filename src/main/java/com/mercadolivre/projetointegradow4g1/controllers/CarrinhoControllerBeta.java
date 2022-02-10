package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.dto.CarrinhoBetaDTO;
import com.mercadolivre.projetointegradow4g1.dto.CarrinhoDTO;
import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.services.CarrinhoServiceBeta;

@RestController
@RequestMapping("/carrinhobeta")
public class CarrinhoControllerBeta {

    @Autowired
    CarrinhoServiceBeta carrinhoService;

    @PostMapping("/salvar")
    public ResponseEntity<CarrinhoBetaDTO> salvar(@RequestBody CarrinhoDTO dto, UriComponentsBuilder uriBuilder) {
        Carrinho carrinho = carrinhoService.salvar(CarrinhoDTO.converte(dto));
        URI uri = uriBuilder
                .path("/carrinhobeta/{id}")
                .buildAndExpand(carrinho.getId())
                .toUri();
        return ResponseEntity.created(uri).body(CarrinhoBetaDTO.converte(carrinho));
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> listar() {
        List<Carrinho> lista = carrinhoService.listar();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoBetaDTO> buscar(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.buscar(id);
        return ResponseEntity.ok().body(CarrinhoBetaDTO.converte(carrinho));
    }
}