package com.mercadolivre.projetointegradow4g1.controllers;

import com.mercadolivre.projetointegradow4g1.dto.EstadoDestinoDTO;
import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import com.mercadolivre.projetointegradow4g1.services.EstadoDestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<EstadoDestinoDTO>> listar() {
        return ResponseEntity.ok(EstadoDestinoDTO.converte(estadoDestinoService.listar()));
    }
}
