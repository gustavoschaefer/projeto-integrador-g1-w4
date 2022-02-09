package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnuncioService {


    private static AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        AnuncioService.anuncioRepository = anuncioRepository;
    }

    public Anuncio salvarAnuncio(Anuncio anuncio){
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listar(){
        return anuncioRepository.findAll();
    }

    public static Anuncio buscarAnuncio(Long id){
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Representante não cadastrado no Armazem informado."));
    }

}


