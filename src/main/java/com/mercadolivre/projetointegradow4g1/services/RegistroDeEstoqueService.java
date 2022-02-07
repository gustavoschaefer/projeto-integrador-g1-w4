package com.mercadolivre.projetointegradow4g1.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.projetointegradow4g1.dto.BuscaLotesDTO;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;

@Service
public class RegistroDeEstoqueService {

    private static RegistroDeEstoqueRepository registroDeEstoqueRepository;

    
    public RegistroDeEstoqueService(RegistroDeEstoqueRepository registroDeEstoqueRepository) {	
		RegistroDeEstoqueService.registroDeEstoqueRepository = registroDeEstoqueRepository;		
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

    public static List<RegistroDeEstoque> listar() {
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

    public BuscaLotesDTO listaProdutosPorLote(Long id, Map<String, String> conservacao) {

        for (Map.Entry<String, String> entry : conservacao.entrySet()) {
            if (entry.getKey().equals("ordem")) {
                if (entry.getValue().equals("L")) {
                    return BuscaLotesDTO.converte(registroDeEstoqueRepository.buscaProdutoLote(id));
                }
                if (entry.getValue().equals("C")) {
                    return BuscaLotesDTO.converte(registroDeEstoqueRepository.buscaProdutoQuantidade(id));
                }
                if (entry.getValue().equals("F")) {
                    return BuscaLotesDTO.converte(registroDeEstoqueRepository.buscaProdutoVal(id));
                }
            }
        }
        return BuscaLotesDTO.converte(registroDeEstoqueRepository.buscaProdutoLote(id));
    }

    public static boolean temEstoque(Lote lote, Integer quantidade) {
        return registroDeEstoqueRepository.buscaLote(lote.getId()).getQuantidadeAtual() >= quantidade;
    }

    public static boolean estaValido(Lote lote, Integer dias) {
        Instant data = Instant.now().plus(dias, ChronoUnit.DAYS);
        return registroDeEstoqueRepository.buscaLote(lote.getId()).getDataValidade().isAfter(data);
    }
}