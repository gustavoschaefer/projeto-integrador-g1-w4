package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.RepresentanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteService {

    private RepresentanteRepository representanteRepository;

    public RepresentanteService(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }

    public Representante salvar(Representante representante) {
        return representanteRepository.save(representante);
    }

    public List<Representante> listar() {
        return representanteRepository.findAll();
    }

    public Representante buscar(Long id) {
        Optional<Representante> optional = representanteRepository.findById(id);
        return optional.orElse(new Representante());
    }
}
