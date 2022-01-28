package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmazemService {

    private ArmazemRepository armazemRepository;

    public ArmazemService(ArmazemRepository armazemRepository) {
        this.armazemRepository = armazemRepository;
    }

    public Armazem salvar(Armazem armazem) {
        return armazemRepository.save(armazem);
    }

    public List<Armazem> listar() {
        return armazemRepository.findAll();
    }

    public Armazem buscar(Long id) {
        Optional<Armazem> optional = armazemRepository.findById(id);
        return optional.orElse(new Armazem());
    }
}
