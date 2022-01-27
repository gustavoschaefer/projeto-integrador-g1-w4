package com.mercadolivre.projetointegradow4g1.controllers;


import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Vendedor> cadastraVendedor(@Valid @RequestBody Vendedor vendedor, UriComponentsBuilder uriBuilder) {
        vendedorService.salvarVendedor(vendedor);
        URI uri = uriBuilder
                .path("/vendedores/{id}")
                .buildAndExpand(vendedor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(vendedor);
    }

    @GetMapping("/obtem")
    public ResponseEntity<List<Vendedor>> obtemVendedores() {
        return ResponseEntity.ok(this.vendedorService.listaVendedor());
    }

    @GetMapping("/obtem/{id}")
    public ResponseEntity<Vendedor> obtemVendedor(@PathVariable Long id) {
        return ResponseEntity.ok(this.vendedorService.buscarVendedor(id));
    }
}
