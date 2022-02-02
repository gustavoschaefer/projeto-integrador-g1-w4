package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Comprador;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.CompradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompradorService {

    private static CompradorRepository compradorRepository;

    public CompradorService(CompradorRepository compradorRepository) {
        CompradorService.compradorRepository = compradorRepository;
    }

    public void salvar(Comprador comprador){
        compradorRepository.save(comprador);
    }

    public List<Comprador> listar(){
        return compradorRepository.findAll();
    }

    public static boolean existe(Comprador comprador) {
        return compradorRepository.findById(comprador.getId()).isPresent();
    }
}
