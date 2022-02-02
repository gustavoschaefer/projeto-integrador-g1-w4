package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.dto.RegistroDeEstoqueDTO;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.services.RegistroDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/registroestoque")
public class RegistroDeEstoqueController {

    @Autowired
    RegistroDeEstoqueService registroDeEstoqueService;

    @PostMapping("/salvar")
    public ResponseEntity<RegistroDeEstoqueDTO> salvarRegistroDeEstoque(@RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.salvarRegistroDeEstoque(RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
        		.path("/registroestoque/{id}")
                .buildAndExpand(regEstRet.getId())
                .toUri();
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> atualizarRegistroDeEstoque(@PathVariable Long id, @RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.atualizarRegistroDeEstoque(id, RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
                .path("/registroestoque/{id}")
                .buildAndExpand(regEstRet.getId())
                .toUri();                
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> buscarRegistroDeEstoque(@PathVariable Long id){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(registroDeEstoqueService.buscarRegistroDeEstoque(id)));
    }

    @GetMapping()
    public ResponseEntity<List<RegistroDeEstoqueDTO>> listar(){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(registroDeEstoqueService.listar()));
    }

}
