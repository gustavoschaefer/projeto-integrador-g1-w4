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

import com.mercadolivre.projetointegradow4g1.dto.LoteDTO;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.services.LoteService;

@RestController
@RequestMapping("/lote")
public class LoteController {
	
	@Autowired
	private LoteService service;
	
	@GetMapping
	public ResponseEntity<List<Lote>> listar(){
		List<Lote> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LoteDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(LoteDTO.converte(service.obter(id)));
	}
	
	@PostMapping
	public ResponseEntity<LoteDTO> salvar(@RequestBody LoteDTO dto, UriComponentsBuilder uriBuilder){
		Lote lote = LoteDTO.converte(dto);
		service.salvar(lote);
		URI uri = uriBuilder
				.path("/lote/{id}")
				.buildAndExpand(lote.getId())
				.toUri();
		return ResponseEntity.created(uri).body(LoteDTO.converte(lote));
	}
}