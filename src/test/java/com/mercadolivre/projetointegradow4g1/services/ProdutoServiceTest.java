package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.ProdutoRepository;
import com.mercadolivre.projetointegradow4g1.repositories.RepresentanteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProdutoServiceTest {
    @Test
    void deveCadastrarProduto() {
        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);

        Produto produto = Produto.builder()
                .id(1L)
                .nome("Produto 1")
                .conservacao(CondicaoConservacao.FRESCO)
                .volumeUni(10.0)
                .build();

        Mockito.when(mock.save(Mockito.any(Produto.class))).thenReturn(produto);

        ProdutoService produtoService = new ProdutoService(mock);
        produtoService.salvar(produto);
        assertNotNull(produto.getId());
    }

    @Test
    void deveBuscarProduto() {
        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);

        Optional<Produto> optionalProduto = Optional.of(Produto.builder()
                .id(1L)
                .nome("Produto 1")
                .conservacao(CondicaoConservacao.FRESCO)
                .volumeUni(10.0)
                .build());

        Mockito.when(mock.findById(1L)).thenReturn(optionalProduto);

        ProdutoService produtoService = new ProdutoService(mock);
        produtoService.buscar(1L);

        assertEquals(1L, optionalProduto.get().getId());
    }

}
