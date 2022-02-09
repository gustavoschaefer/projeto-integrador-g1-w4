package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void deveListarProdutos() {
        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);

        List<Produto> produtos = Arrays.asList(
                Produto.builder().id(1L).nome("Produto 1").conservacao(CondicaoConservacao.FRESCO).volumeUni(10.0).build(),
                Produto.builder().id(2L).nome("Produto 2").conservacao(CondicaoConservacao.RESFRIADO).volumeUni(10.0).build(),
                Produto.builder().id(3L).nome("Produto 3").conservacao(CondicaoConservacao.CONGELADO).volumeUni(10.0).build()
        );

        Mockito.when(mock.findAll()).thenReturn(produtos);

        ProdutoService produtoService = new ProdutoService(mock);

        assertEquals(1L, produtoService.listar(Map.of("conservacao", "FS")).get(0).getId());
        assertEquals(2L, produtoService.listar(Map.of("conservacao", "RF")).get(0).getId());
        assertEquals(3L, produtoService.listar(Map.of("conservacao", "FF")).get(0).getId());
    }

    @Test
    void naoDeveListarProdutos() {
        ProdutoRepository mock = Mockito.mock(ProdutoRepository.class);

        List<Produto> produtos = new ArrayList<>();

        Mockito.when(mock.findAll()).thenReturn(produtos);

        ProdutoService produtoService = new ProdutoService(mock);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                produtoService.listar(Map.of("conservacao", "FS")));
        assertTrue(exception.getMessage().contains("Nenhum produto registrado."));
    }


}
