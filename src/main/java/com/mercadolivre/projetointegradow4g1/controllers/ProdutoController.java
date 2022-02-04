package com.mercadolivre.projetointegradow4g1.controllers;


import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mercadolivre.projetointegradow4g1.dto.ProdutoDTO;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastraProduto(@Valid @RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        produtoService.salvarProduto(produto);
        URI uri = uriBuilder
                .path("/produto/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(ProdutoDTO.converte(produto));
    }

    @GetMapping("/obtem")
    public ResponseEntity<List<ProdutoDTO>> obtemProdutos(@RequestParam Map<String, String> conservacao) {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProdutos(conservacao)));
    }

    @GetMapping("/obtem/{id}")
    public ResponseEntity<ProdutoDTO> obtemProduto(@PathVariable Long id, @RequestParam Map<String, String> conservacao) {
        return ResponseEntity.ok(ProdutoDTO.converte(produtoService.listaProdutosPorLote(id, conservacao)));
    }

}
