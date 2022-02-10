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

    @PostMapping("/salvar")
    public ResponseEntity<VendedorDTO> salvar(@Valid @RequestBody Vendedor vendedor, UriComponentsBuilder uriBuilder) {
        vendedorService.salvar(vendedor);
        URI uri = uriBuilder
                .path("/vendedor/{id}")
                .buildAndExpand(vendedor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(VendedorDTO.converte(vendedor));
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listar() {
        return ResponseEntity.ok(VendedorDTO.converte(this.vendedorService.listar()));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<VendedorDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(VendedorDTO.converte(this.vendedorService.buscar(id)));
    }
}