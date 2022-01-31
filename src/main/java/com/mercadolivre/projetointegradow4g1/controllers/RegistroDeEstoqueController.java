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
    public ResponseEntity<RegistroDeEstoqueDTO> postEstoque(@RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.postRegistroDeEstoque(RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
                .path("/anuncio/listar")
                .build().toUri();
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> putEstoque(@PathVariable Long id, @RequestBody RegistroDeEstoqueDTO registroDeEstoque, UriComponentsBuilder uriBuilder){
        RegistroDeEstoque regEstRet = registroDeEstoqueService.putRegistroDeEstoque(id, RegistroDeEstoqueDTO.converte(registroDeEstoque));
        URI uri = uriBuilder
                .path("/anuncio/listar")
                .build().toUri();
        return ResponseEntity.created(uri).body(RegistroDeEstoqueDTO.converte(regEstRet));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RegistroDeEstoqueDTO> getRegistroEstoque(@PathVariable Long id){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(registroDeEstoqueService.buscarRegistroDeEstoque(id)));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RegistroDeEstoqueDTO>> listar(){
        return ResponseEntity.ok(RegistroDeEstoqueDTO.converte(registroDeEstoqueService.listar()));
    }

}
