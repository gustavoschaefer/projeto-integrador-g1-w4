package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistroDeEstoqueService {

    private final RegistroDeEstoqueRepository registroDeEstoqueRepository;

    
    public RegistroDeEstoqueService(RegistroDeEstoqueRepository registroDeEstoqueRepository) {	
		this.registroDeEstoqueRepository = registroDeEstoqueRepository;		
	}

    public RegistroDeEstoque salvarRegistroDeEstoque(RegistroDeEstoque registroDeEstoque) {

        if (!RepresentanteService.existeNoArmazem(registroDeEstoque.getRepresentante(), registroDeEstoque.getSetor().getArmazem().getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Representante não cadastrado no Armazem informado.");
        }

        if(!SetorService.existe(registroDeEstoque.getSetor())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor inválido.");
        }
        double volumeEstoque = 0;
        for (Lote lote : registroDeEstoque.getLotes()){
            volumeEstoque += lote.getVolumeTotal();

            if (!SetorService.confereTipo(registroDeEstoque.getSetor(),lote.getProduto().getConservacao())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor não corresponde ao tipo do produto: " +lote.getProduto().getNome());
            }
        }

        if(!SetorService.temCapacidade(registroDeEstoque.getSetor(),volumeEstoque)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Espaço insuficiente no setor.");
        }

        SetorService.atualizaCapacidade(registroDeEstoque.getSetor(),volumeEstoque);

    	return registroDeEstoqueRepository.save(registroDeEstoque);
    }

    public List<RegistroDeEstoque> listar(){
        return registroDeEstoqueRepository.findAll();
    }

    public RegistroDeEstoque buscarRegistroDeEstoque(Long id){
        Optional<RegistroDeEstoque> registroDeEstoque = registroDeEstoqueRepository.findById(id);
        return registroDeEstoque.orElse(new RegistroDeEstoque());
    }

    public RegistroDeEstoque atualizarRegistroDeEstoque(Long id, RegistroDeEstoque registroDeEstoque) {
        RegistroDeEstoque registroDeEstoqueRet = registroDeEstoqueRepository.getById(id);
        registroDeEstoqueRet.setData(registroDeEstoque.getData());
        registroDeEstoqueRet.setSetor(registroDeEstoque.getSetor());
        registroDeEstoqueRet.setRepresentante(registroDeEstoque.getRepresentante());
        registroDeEstoqueRet.setLotes(registroDeEstoque.getLotes());
        return registroDeEstoqueRepository.save(registroDeEstoqueRet);
    }
}