package com.mercadolivre.projetointegradow4g1.controllers;


import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastraProduto(@Valid @RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        produtoService.salvarProduto(produto);
        URI uri = uriBuilder
                .path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping("/obtem")
    public ResponseEntity<List<Produto>> obtemProdutos() {
        return ResponseEntity.ok(this.produtoService.listaProdutos());
    }

    @GetMapping("/obtem/{id}")
    public ResponseEntity<Produto> obtemProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProduto(id));
    }
}
