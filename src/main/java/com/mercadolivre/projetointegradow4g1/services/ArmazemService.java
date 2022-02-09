package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.mercadolivre.projetointegradow4g1.dto.ProdutoArmazemDTO;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;

@Service
public class ArmazemService {

    private static ArmazemRepository armazemRepository;

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
        return armazemRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Armazem não registrado."));
    }

    public synchronized static boolean existe(Armazem armazem) {
        return armazemRepository.findById(armazem.getId()).isPresent();
    }

    public ProdutoArmazemDTO buscaProdutoPorArmazem(Long id) {
        return ProdutoArmazemDTO.converte(armazemRepository.buscaProdutoArmazem(id));
    }
}