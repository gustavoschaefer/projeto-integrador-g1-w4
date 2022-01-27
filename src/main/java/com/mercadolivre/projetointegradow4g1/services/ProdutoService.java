package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvarProduto(Produto produto) {
        this.produtoRepository.save(produto);
    }

    public List<Produto> listaProdutos() {
        return this.produtoRepository.findAll();
    }

    public Produto buscarProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.get();
    }
}
