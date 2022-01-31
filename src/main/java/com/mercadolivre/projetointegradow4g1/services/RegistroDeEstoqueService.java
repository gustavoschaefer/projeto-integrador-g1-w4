package com.mercadolivre.projetointegradow4g1.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.LoteRepository;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;

@Service
public class RegistroDeEstoqueService {

    private RegistroDeEstoqueRepository registroDeEstoqueRepository;
    private LoteRepository loteRepository;

    public RegistroDeEstoqueService(RegistroDeEstoqueRepository registroDeEstoqueRepository, LoteRepository loteRepository) {
        this.registroDeEstoqueRepository = registroDeEstoqueRepository;
        this.loteRepository = loteRepository;
    }

    public RegistroDeEstoque postRegistroDeEstoque(RegistroDeEstoque registroDeEstoque) {
        Set<Lote> loteSet = new HashSet<>();
        registroDeEstoque.getLotes().forEach(lote -> {
            Optional<Lote> loteOptional = loteRepository.findById(lote.getId());
            loteSet.add(loteOptional.get());
            loteOptional.get().setRegistroDeEstoque(registroDeEstoque);
            loteRepository.save(loteOptional.get());
        });
        registroDeEstoque.setLotes(loteSet);
        return registroDeEstoqueRepository.save(registroDeEstoque);
    }

    public List<RegistroDeEstoque> listar(){
        return registroDeEstoqueRepository.findAll();
    }

    public RegistroDeEstoque buscarRegistroDeEstoque(Long id){
        Optional<RegistroDeEstoque> registroDeEstoque = registroDeEstoqueRepository.findById(id);
        return registroDeEstoque.orElse(new RegistroDeEstoque());
    }
}
