package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroDeEstoqueService {


    private RegistroDeEstoqueRepository registroDeEstoqueRepository;

    public RegistroDeEstoqueService(RegistroDeEstoqueRepository registroDeEstoqueRepository) {
        this.registroDeEstoqueRepository = registroDeEstoqueRepository;
    }

    public RegistroDeEstoque postRegistroDeEstoque(RegistroDeEstoque registroDeEstoque){
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
