package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;

@Service
public class ArmazemService {

    private static ArmazemRepository armazemRepository;

    public static boolean valida(Representante representante) {
        if (armazemRepository.findById(representante.getArmazem().getId()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public ArmazemService(ArmazemRepository armazemRepository) {
        ArmazemService.armazemRepository = armazemRepository;
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
