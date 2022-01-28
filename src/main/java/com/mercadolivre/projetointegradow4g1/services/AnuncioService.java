package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

 /*   public void postAnuncio(Anuncio anuncio){
        this.anuncioRepository.save(anuncio);
    }*/


public ResponseEntity<Anuncio> postAnuncio(Anuncio anuncio){
        try {
            anuncioRepository.save(anuncio);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
