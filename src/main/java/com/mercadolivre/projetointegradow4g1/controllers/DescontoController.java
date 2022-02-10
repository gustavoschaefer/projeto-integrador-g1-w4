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

import com.mercadolivre.projetointegradow4g1.dto.DescontoDTO;
import com.mercadolivre.projetointegradow4g1.entities.Desconto;
import com.mercadolivre.projetointegradow4g1.services.DescontoService;

@RestController
@RequestMapping("/desconto")
public class DescontoController {
	
	@Autowired
	DescontoService descontoService;
	
	@PostMapping("/salvar")
	public ResponseEntity<DescontoDTO> cadastrar(@RequestBody Desconto desconto, UriComponentsBuilder uriBuilder){
		descontoService.salvar(desconto.getVendedor(), desconto.getQuantidade(), desconto.getPorcentagem());
		URI uri = uriBuilder
				.path("/desconto/{id}")
				.buildAndExpand(desconto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(DescontoDTO.converte(desconto));
	}
	
	@GetMapping
	public ResponseEntity<List<DescontoDTO>> listar(){
		return ResponseEntity.ok(DescontoDTO.converte(descontoService.listar()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DescontoDTO> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(DescontoDTO.converte(this.descontoService.buscar(id)));		
	}
}