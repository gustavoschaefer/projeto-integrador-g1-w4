package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.RepresentanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentanteService {

    private static RepresentanteRepository representanteRepository;

    public RepresentanteService(RepresentanteRepository representanteRepository) {
        RepresentanteService.representanteRepository = representanteRepository;
    }

    public Representante salvar(Representante representante) {
        if (ArmazemService.existe(representante.getArmazem())) {
            return representanteRepository.save(representante);
        } else {
            throw new RuntimeException("Armazem invalido");
        }
    }

    public List<Representante> listar() {
        return representanteRepository.findAll();
    }

    public Representante buscar(Long id) {
        return representanteRepository.findById(id).orElse(new Representante());
    }

    public static boolean existe(Representante representante) {
        return representanteRepository.findById(representante.getId()).isPresent();
    }

    public static boolean armazemExiste(Representante representante) {
        return representanteRepository.findById(representante.getId()).get().getArmazem().equals(representante.getArmazem());
    }
}
