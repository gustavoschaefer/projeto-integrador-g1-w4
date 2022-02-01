package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;

@Service
public class RegistroDeEstoqueService {

    private RegistroDeEstoqueRepository registroDeEstoqueRepository;
    
    public RegistroDeEstoqueService(RegistroDeEstoqueRepository registroDeEstoqueRepository) {	
		this.registroDeEstoqueRepository = registroDeEstoqueRepository;		
	}

    public RegistroDeEstoque postRegistroDeEstoque(RegistroDeEstoque registroDeEstoque) {
    	return registroDeEstoqueRepository.save(registroDeEstoque);
    }

    public List<RegistroDeEstoque> listar(){
        return registroDeEstoqueRepository.findAll();
    }

    public RegistroDeEstoque buscarRegistroDeEstoque(Long id){
        Optional<RegistroDeEstoque> registroDeEstoque = registroDeEstoqueRepository.findById(id);
        return registroDeEstoque.orElse(new RegistroDeEstoque());
    }

    public RegistroDeEstoque putRegistroDeEstoque(Long id, RegistroDeEstoque registroDeEstoque) {
        RegistroDeEstoque registroDeEstoqueRet = registroDeEstoqueRepository.getById(id);
        registroDeEstoqueRet.setData(registroDeEstoque.getData());
        registroDeEstoqueRet.setSetor(registroDeEstoque.getSetor());
        registroDeEstoqueRet.setRepresentante(registroDeEstoque.getRepresentante());
        registroDeEstoqueRet.setLotes(registroDeEstoque.getLotes());
        return registroDeEstoqueRepository.save(registroDeEstoqueRet);
    }
}