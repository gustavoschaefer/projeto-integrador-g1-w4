package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import com.mercadolivre.projetointegradow4g1.dto.AnuncioDTO;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;

import javax.validation.Valid;

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


