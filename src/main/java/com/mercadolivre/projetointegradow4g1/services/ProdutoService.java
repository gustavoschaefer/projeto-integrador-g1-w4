package com.mercadolivre.projetointegradow4g1.services;

import java.util.*;
import java.util.stream.Collectors;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.repositories.ProdutoRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvarProduto(Produto produto) {
        this.produtoRepository.save(produto);
    }

    public Produto buscarProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.get();
    }

    public List<Produto> listaProdutos(Map<String, String> conservacao) {
        List<Produto> produtos = produtoRepository.findAll();
        for (Map.Entry<String, String> entry : conservacao.entrySet()) {
            if (entry.getKey().equals("conservacao")) {
                if (entry.getValue().equals("FS")) {
                    produtos = produtos.stream()
                            .filter(p -> p.getConservacao().toString().equals("FRESCO")).collect(Collectors.toList());
                }
                if (entry.getValue().equals("RF")) {
                    produtos = produtos.stream()
                            .filter(p -> p.getConservacao().toString().equals("RESFRIADO")).collect(Collectors.toList());
                }
                if (entry.getValue().equals("FF")) {
                    produtos = produtos.stream()
                            .filter(p -> p.getConservacao().toString().equals("CONGELADO")).collect(Collectors.toList());
                }
            }
        }
        if (produtos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto registrado.");
        }
        return produtos;
    }
    public Produto listaProdutosPorLote(Long id, Map<String, String> conservacao) {
        Optional<Produto> optProduto = produtoRepository.findById(id);
        List<Lote> lotes =  optProduto.get().getLotes();



        for (Map.Entry<String, String> entry : conservacao.entrySet()) {
            if (entry.getKey().equals("ordem")) {
                if (entry.getValue().equals("L")) {


                }
                if (entry.getValue().equals("C")) {

                    Collections.sort(lotes, Lote.ordemCrescenteQuantidade);
                }
                if (entry.getValue().equals("F")) {

                    Collections.sort(lotes, Lote.ordemCrescenteValidade);
                }
            }
        }
        if (optProduto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto registrado.");
        }
        Produto produto = optProduto.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        produto.setLotes(lotes);

        return produto;
    }

}
