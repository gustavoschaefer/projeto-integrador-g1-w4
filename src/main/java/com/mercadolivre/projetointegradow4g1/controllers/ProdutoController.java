package com.mercadolivre.projetointegradow4g1.controllers;


import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.services.ProdutoService;

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
    public ResponseEntity<List<Produto>> obtemProdutos(@RequestParam Map<String, String> conservacao) {
        return ResponseEntity.ok(this.produtoService.listaProdutos(conservacao));
    }

    @GetMapping("/obtem/{id}")
    public ResponseEntity<Produto> obtemProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProduto(id));
    }
//    @GetMapping("/obtem/{conservacao}")
//    public ResponseEntity<List<Produto>> obtemProdutos(@PathVariable String conservacao){
//        return ResponseEntity.ok(this.produtoService.listaProdutos(conservacao));
//    }
}
