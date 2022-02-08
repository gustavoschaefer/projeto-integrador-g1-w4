package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.dto.SetorDTO;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.services.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

	@Autowired
	private SetorService service;

	@GetMapping
	public ResponseEntity<List<Setor>> listar() {
		List<Setor> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SetorDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(SetorDTO.converte(service.obter(id)));
	}

	@PostMapping
	public ResponseEntity<SetorDTO> salvar(@Valid @RequestBody SetorDTO dto, UriComponentsBuilder uriBuilder) {
		Setor setor = SetorDTO.converte(dto);
		service.salvar(setor);
		URI uri = uriBuilder
				.path("/setores/{id}")
				.buildAndExpand(setor.getId())
				.toUri();
		return ResponseEntity.created(uri).body(SetorDTO.converte(setor));
	}

	@GetMapping("/buscalote/{id}/{dias}")
	public ResponseEntity<List<SetorRepository.SetorTmp>> buscarLote(@PathVariable Long id,
																	 @RequestParam Map<String, String> conservacao,
																	 @PathVariable Integer dias) {
		return ResponseEntity.ok(service.buscaLostesPorSetor(id, conservacao, dias));
	}
}