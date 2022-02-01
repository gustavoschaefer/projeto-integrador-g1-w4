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

import com.mercadolivre.projetointegradow4g1.dto.CompraDTO;
import com.mercadolivre.projetointegradow4g1.entities.Compra;
import com.mercadolivre.projetointegradow4g1.services.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	CompraService compraService;
	
	@PostMapping("/salvar")
	public ResponseEntity<CompraDTO> salvar(@RequestBody CompraDTO dto, UriComponentsBuilder uriBuilder){
		Compra compra = compraService.salvar(CompraDTO.converte(dto));
        URI uri = uriBuilder
                .path("/compra/{id}")
                .buildAndExpand(compra.getId())
                .toUri();
        return ResponseEntity.created(uri).body(CompraDTO.converte(compra));
	}
	
	@GetMapping("/listar")
    public ResponseEntity<List<CompraDTO>> listar(){
        return ResponseEntity.ok().body(CompraDTO.converte(compraService.listar()));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(CompraDTO.converte(compraService.buscar(id)));
    }
}