package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.RepresentanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Representante invalido.");
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

    public static boolean existeNoArmazem(Representante representante, Long idArmazem) {
        Optional<Representante> representanteOpt = representanteRepository.findById(representante.getId());
        return representanteOpt.map(value -> value.getArmazem().getId().equals(idArmazem)).orElse(false);
    }
}
