package com.mercadolivre.projetointegradow4g1.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.projetointegradow4g1.dto.EstadoDestinoDTO;
import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import com.mercadolivre.projetointegradow4g1.services.EstadoDestinoService;

@RestController
@RequestMapping("/destino")
public class EstadoDeDestinoController {

    @Autowired
    EstadoDestinoService estadoDestinoService;

    @PostMapping("/salvar")
    public ResponseEntity<EstadoDestinoDTO> salvar(@Valid @RequestBody EstadoDestinoDTO estadoDestinoDTO, UriComponentsBuilder uriBuilder){
        EstadoDestino estadoDestino = estadoDestinoService.salvar(EstadoDestinoDTO.converte(estadoDestinoDTO));
        URI uri = uriBuilder
                .path("/destino/{id}")
                .buildAndExpand(estadoDestino.getId())
                .toUri();
        return ResponseEntity.created(uri).body(EstadoDestinoDTO.converte(estadoDestino));
    }

    @GetMapping
    public ResponseEntity<List<EstadoDestinoDTO>> listar() {
        return ResponseEntity.ok(EstadoDestinoDTO.converte(estadoDestinoService.listar()));
    }
}