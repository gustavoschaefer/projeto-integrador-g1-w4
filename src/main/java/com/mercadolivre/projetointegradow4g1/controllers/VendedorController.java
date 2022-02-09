package com.mercadolivre.projetointegradow4g1.controllers;


import com.mercadolivre.projetointegradow4g1.dto.VendedorDTO;
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
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<VendedorDTO> cadastraVendedor(@Valid @RequestBody Vendedor vendedor, UriComponentsBuilder uriBuilder) {
        vendedorService.salvar(vendedor);
        URI uri = uriBuilder
                .path("/vendedor/{id}")
                .buildAndExpand(vendedor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(VendedorDTO.converte(vendedor));
    }

    @GetMapping("/obtem")
    public ResponseEntity<List<VendedorDTO>> obtemVendedores() {
        return ResponseEntity.ok(VendedorDTO.converte(this.vendedorService.listar()));
    }

    @GetMapping("/obtem/{id}")
    public ResponseEntity<VendedorDTO> obtemVendedor(@PathVariable Long id) {
        return ResponseEntity.ok(VendedorDTO.converte(this.vendedorService.buscar(id)));
    }
}