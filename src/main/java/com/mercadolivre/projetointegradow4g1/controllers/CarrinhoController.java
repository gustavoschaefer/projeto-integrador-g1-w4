package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.dto.CarrinhoBuscarDTO;
import com.mercadolivre.projetointegradow4g1.dto.CarrinhoDTO;
import com.mercadolivre.projetointegradow4g1.dto.CarrinhoSalvarDTO;
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
    public ResponseEntity<CarrinhoSalvarDTO> salvar(@RequestBody CarrinhoDTO dto, UriComponentsBuilder uriBuilder) {
        Carrinho carrinho = carrinhoService.salvar(CarrinhoDTO.converte(dto));
        URI uri = uriBuilder
                .path("/carrinho/{id}")
                .buildAndExpand(carrinho.getId())
                .toUri();
        return ResponseEntity.created(uri).body(CarrinhoDTO.converteSalvar(carrinho));
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> listar() {
        List<Carrinho> lista = carrinhoService.listar();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoBuscarDTO> buscar(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.buscar(id);
        return ResponseEntity.ok().body(CarrinhoDTO.converteBuscar(carrinho));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoBuscarDTO> alterar(@PathVariable Long id, @RequestBody CarrinhoDTO dto, UriComponentsBuilder uriBuilder) {
        Carrinho carrinho = carrinhoService.alterar(id, CarrinhoDTO.converte(dto));
        URI uri = uriBuilder
                .path("/carrinho/{id}")
                .buildAndExpand(carrinho.getId())
                .toUri();
        return ResponseEntity.created(uri).body(CarrinhoDTO.converteBuscar(carrinho));
    }

}
