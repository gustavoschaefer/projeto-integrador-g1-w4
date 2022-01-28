package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.services.RegistroDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/registroestoque")
public class RegistroDeEstoqueController {

    @Autowired
    RegistroDeEstoqueService registroDeEstoqueService;


    @PostMapping("/salvar")
    public ResponseEntity<RegistroDeEstoque> postEstoque(@RequestBody RegistroDeEstoque registroDeEstoque, UriComponentsBuilder uriBuilder){
        registroDeEstoqueService.postRegistroDeEstoque(registroDeEstoque);
        URI uri = uriBuilder
                .path("/anuncio/listar")
                .build().toUri();
        return ResponseEntity.created(uri).body(registroDeEstoque);
    }

}
