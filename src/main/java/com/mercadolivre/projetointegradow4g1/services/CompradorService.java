package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Comprador;
import com.mercadolivre.projetointegradow4g1.repositories.CompradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorService {

    private CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    public void salvar(Comprador comprador){
        this.compradorRepository.save(comprador);
    }

    public List<Comprador> listar(){
        return this.compradorRepository.findAll();
    }
}
