package com.mercadolivre.projetointegradow4g1.services;

import java.sql.SQLOutput;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistroDeEstoqueService {

    private static RegistroDeEstoqueRepository registroDeEstoqueRepository;

    
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

//    public List<ProdutoTmp> listaProdutosPorLote(Long id, Map<String, String> conservacao) {
//        Optional<Produto> optProduto = produtoRepository.findById(id);
//        List<Lote> lotes =  optProduto.get().getLotes();
//        String filtro = null;
//        Produto produto = new Produto();
//
//
//
//        for (Map.Entry<String, String> entry : conservacao.entrySet()) {
//            if (entry.getKey().equals("ordem")) {
//                if (entry.getValue().equals("L")) {
//                    filtro = "lote";
//
//                }
//                if (entry.getValue().equals("C")) {
//                    filtro = "quantidade";
//                    Collections.sort(lotes, Lote.ordemCrescenteQuantidade);
//                }
//                if (entry.getValue().equals("F")) {
//
//                    filtro = "validade";
//                   Collections.sort(lotes, Lote.ordemCrescenteValidade);
//                }
//            }
//        }
//        if (optProduto.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto registrado.");
//        }
//        Produto produto = optProduto.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
//        produto.setLotes(lotes);
//
//        return produto;
//    }

    public static boolean temEstoque(Lote lote, Integer quantidade) {
        return registroDeEstoqueRepository.buscaLote(lote.getId()).getQuantidadeAtual() >= quantidade;
    }

    public static boolean estaValido(Lote lote, Integer dias) {
        Instant data = Instant.now().plus(dias, ChronoUnit.DAYS);
        return registroDeEstoqueRepository.buscaLote(lote.getId()).getDataValidade().isAfter(data);
    }
}