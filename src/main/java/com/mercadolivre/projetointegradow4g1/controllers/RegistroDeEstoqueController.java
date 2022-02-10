package com.mercadolivre.projetointegradow4g1.controllers;


import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.dto.BuscaLotesDTO;
import com.mercadolivre.projetointegradow4g1.dto.RegistroDeEstoqueDTO;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.services.RegistroDeEstoqueService;

@Controller
@RequestMapping("/registroestoque")
public class RegistroDeEstoqueController {

    @Autowired
    RegistroDeEstoqueService registroDeEstoqueService;

    @PostMapping("/salvar")
    public ResponseEntity<RegistroDeEstoqueDTO> salvar(@RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.salvar(RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
        		.path("/registroestoque/{id}")
                .buildAndExpand(regEstRet.getId())
                .toUri();
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> atualizar(@PathVariable Long id, @RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.atualizar(id, RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
                .path("/registroestoque/{id}")
                .buildAndExpand(regEstRet.getId())
                .toUri();                
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(registroDeEstoqueService.buscar(id)));
    }

    @GetMapping
    public ResponseEntity<List<RegistroDeEstoqueDTO>> listar(){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(RegistroDeEstoqueService.listar()));
    }


    @GetMapping("/produtolote/{id}")
    public ResponseEntity<BuscaLotesDTO> buscarProduto(@PathVariable Long id, @RequestParam Map<String, String> conservacao) {
        return ResponseEntity.ok(registroDeEstoqueService.listaProdutosPorLote(id, conservacao));
    }

}
