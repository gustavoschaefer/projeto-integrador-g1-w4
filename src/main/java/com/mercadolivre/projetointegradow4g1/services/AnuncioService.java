package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {


    private AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    public Anuncio postAnuncio(Anuncio anuncio){
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listar(){
        return anuncioRepository.findAll();
    }

    public Anuncio buscarAnuncio(Long id){
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.orElse(new Anuncio());
    }

}


