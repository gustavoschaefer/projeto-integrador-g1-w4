package com.mercadolivre.projetointegradow4g1.services.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import com.mercadolivre.projetointegradow4g1.services.RegistroDeEstoqueService;
import com.mercadolivre.projetointegradow4g1.services.RepresentanteService;
import com.mercadolivre.projetointegradow4g1.services.SetorService;

public class RegistroDeEstoqueServiceTest {

    @Test
    public void deveCadastrarRegistroDeEstoque(){
        RegistroDeEstoqueRepository mock = Mockito.mock(RegistroDeEstoqueRepository.class);

        RegistroDeEstoque registroDeEstoque = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder()
                        .id(1L)
                        .nome("Setor 1")
                        .tipo(CondicaoConservacao.FRESCO)
                        .capacidadeTotal(20.0)
                        .capacidadeAtual(20.0)
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build()).build())
                .lotes(Collections.singleton(Lote.builder()
                        .id(1L)
                        .quantidadeInicial(20)
                        .quantidadeAtual(20)
                        .dataFabricacao(Instant.parse("2018-05-07T15:20:45.765Z"))
                        .dataValidade(Instant.parse("2020-05-07T15:20:45.765Z"))
                        .temperaturaAtual(20.0)
                        .temperaturaMinima(10.0)
                        .produto(Produto.builder()
                                .id(1L)
                                .nome("picanha")
                                .conservacao(CondicaoConservacao.FRESCO)
                                .volumeUni(100.0).build())
                        .build()))
                .representante(Representante.builder()
                        .id(1L)
                        .nome("Jose")
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build())
                        .build())
                .build();

        Mockito.when(mock.save(Mockito.any(RegistroDeEstoque.class))).thenReturn(registroDeEstoque);


        RegistroDeEstoqueService registroDeEstoqueService = new RegistroDeEstoqueService(mock);

        try(MockedStatic<RepresentanteService> representanteServiceMockedStatic = Mockito.mockStatic(RepresentanteService.class)) {
            representanteServiceMockedStatic.when(
                    () -> RepresentanteService.existeNoArmazem(Mockito.any(Representante.class), Mockito.anyLong())
            ).thenReturn(true);

            try (MockedStatic<SetorService> setorServiceMockedStatic = Mockito.mockStatic(SetorService.class)){
                setorServiceMockedStatic.when(
                        () -> SetorService.existe(Mockito.any(Setor.class))
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.confereTipo(Mockito.any(Setor.class),Mockito.any(CondicaoConservacao.class))
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.temCapacidade(Mockito.any(Setor.class),Mockito.anyDouble())
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.temCapacidade(Mockito.any(Setor.class),Mockito.anyDouble())
                ).thenReturn(true);

                registroDeEstoqueService.salvar(registroDeEstoque);
                assertNotNull(registroDeEstoque.getId());
            }
        }

    }

    @Test
    public void deveListarRegistroDeCompra(){
        RegistroDeEstoqueRepository mock = Mockito.mock(RegistroDeEstoqueRepository.class);

        ArrayList<RegistroDeEstoque> lista = new ArrayList<>();
        RegistroDeEstoque registroDeEstoque1 = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder()
                        .id(1L)
                        .nome("Setor 1")
                        .tipo(CondicaoConservacao.FRESCO)
                        .capacidadeTotal(20.0)
                        .capacidadeAtual(20.0)
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build()).build())
                .lotes(Collections.singleton(Lote.builder()
                        .id(1L)
                        .quantidadeInicial(20)
                        .quantidadeAtual(20)
                        .dataFabricacao(Instant.parse("2018-05-07T15:20:45.765Z"))
                        .dataValidade(Instant.parse("2020-05-07T15:20:45.765Z"))
                        .temperaturaAtual(20.0)
                        .temperaturaMinima(10.0)
                        .produto(Produto.builder()
                                .id(1L)
                                .nome("picanha")
                                .conservacao(CondicaoConservacao.FRESCO)
                                .volumeUni(100.0).build())
                        .build()))
                .representante(Representante.builder()
                        .id(1L)
                        .nome("Jose")
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build())
                        .build())
                .build();

        RegistroDeEstoque registroDeEstoque2 = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder()
                        .id(1L)
                        .nome("Setor 1")
                        .tipo(CondicaoConservacao.FRESCO)
                        .capacidadeTotal(20.0)
                        .capacidadeAtual(20.0)
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build()).build())
                .lotes(Collections.singleton(Lote.builder()
                        .id(1L)
                        .quantidadeInicial(20)
                        .quantidadeAtual(20)
                        .dataFabricacao(Instant.parse("2018-05-07T15:20:45.765Z"))
                        .dataValidade(Instant.parse("2020-05-07T15:20:45.765Z"))
                        .temperaturaAtual(20.0)
                        .temperaturaMinima(10.0)
                        .produto(Produto.builder()
                                .id(1L)
                                .nome("picanha")
                                .conservacao(CondicaoConservacao.FRESCO)
                                .volumeUni(100.0).build())
                        .build()))
                .representante(Representante.builder()
                        .id(1L)
                        .nome("Jose")
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build())
                        .build())
                .build();

        lista.add(registroDeEstoque1);
        lista.add(registroDeEstoque2);

        Mockito.when(mock.findAll()).thenReturn(lista);

        new RegistroDeEstoqueService(mock);
        assertEquals(2, RegistroDeEstoqueService.listar().size());
    }

    @Test
    public void deveBuscarRegistroDeEstoque(){
        RegistroDeEstoqueRepository mock = Mockito.mock(RegistroDeEstoqueRepository.class);

        RegistroDeEstoque registroDeEstoque = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder()
                        .id(1L)
                        .nome("Setor 1")
                        .tipo(CondicaoConservacao.FRESCO)
                        .capacidadeTotal(20.0)
                        .capacidadeAtual(20.0)
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build()).build())
                .lotes(Collections.singleton(Lote.builder()
                        .id(1L)
                        .quantidadeInicial(20)
                        .quantidadeAtual(20)
                        .dataFabricacao(Instant.parse("2018-05-07T15:20:45.765Z"))
                        .dataValidade(Instant.parse("2020-05-07T15:20:45.765Z"))
                        .temperaturaAtual(20.0)
                        .temperaturaMinima(10.0)
                        .produto(Produto.builder()
                                .id(1L)
                                .nome("picanha")
                                .conservacao(CondicaoConservacao.FRESCO)
                                .volumeUni(100.0).build())
                        .build()))
                .representante(Representante.builder()
                        .id(1L)
                        .nome("Jose")
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build())
                        .build())
                .build();

        Mockito.when(mock.findById(1L)).thenReturn(Optional.ofNullable(registroDeEstoque));
        RegistroDeEstoqueService registroDeEstoqueService = new RegistroDeEstoqueService(mock);
        registroDeEstoqueService.buscar(1L);

        assertEquals(1L,registroDeEstoque.getId());
    }

    @Test
    public void deveAtualizarRegistroDeEstoque(){
        RegistroDeEstoqueRepository mock = Mockito.mock(RegistroDeEstoqueRepository.class);

        RegistroDeEstoque registroDeEstoque = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder()
                        .id(1L)
                        .nome("Setor 1")
                        .tipo(CondicaoConservacao.FRESCO)
                        .capacidadeTotal(20.0)
                        .capacidadeAtual(20.0)
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build()).build())
                .lotes(Collections.singleton(Lote.builder()
                        .id(1L)
                        .quantidadeInicial(20)
                        .quantidadeAtual(20)
                        .dataFabricacao(Instant.parse("2018-05-07T15:20:45.765Z"))
                        .dataValidade(Instant.parse("2020-05-07T15:20:45.765Z"))
                        .temperaturaAtual(20.0)
                        .temperaturaMinima(10.0)
                        .produto(Produto.builder()
                                .id(1L)
                                .nome("picanha")
                                .conservacao(CondicaoConservacao.FRESCO)
                                .volumeUni(100.0).build())
                        .build()))
                .representante(Representante.builder()
                        .id(1L)
                        .nome("Jose")
                        .armazem(Armazem.builder()
                                .id(1L)
                                .nome("Armazem 1")
                                .descricao("Descrição Armazem 1")
                                .build())
                        .build())
                .build();

        Mockito.when(mock.save(Mockito.any(RegistroDeEstoque.class))).thenReturn(registroDeEstoque);
        Mockito.when(mock.getById(Mockito.anyLong())).thenReturn(registroDeEstoque);


        RegistroDeEstoqueService registroDeEstoqueService = new RegistroDeEstoqueService(mock);

        try(MockedStatic<RepresentanteService> representanteServiceMockedStatic = Mockito.mockStatic(RepresentanteService.class)) {
            representanteServiceMockedStatic.when(
                    () -> RepresentanteService.existeNoArmazem(Mockito.any(Representante.class), Mockito.anyLong())
            ).thenReturn(true);

            try (MockedStatic<SetorService> setorServiceMockedStatic = Mockito.mockStatic(SetorService.class)){
                setorServiceMockedStatic.when(
                        () -> SetorService.existe(Mockito.any(Setor.class))
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.confereTipo(Mockito.any(Setor.class),Mockito.any(CondicaoConservacao.class))
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.temCapacidade(Mockito.any(Setor.class),Mockito.anyDouble())
                ).thenReturn(true);
                setorServiceMockedStatic.when(
                        () -> SetorService.temCapacidade(Mockito.any(Setor.class),Mockito.anyDouble())
                ).thenReturn(true);

                registroDeEstoqueService.atualizar(1L, registroDeEstoque);

                registroDeEstoque.setData(Instant.parse("2018-05-07T15:20:45.765Z"));
                registroDeEstoque.getSetor().setCapacidadeAtual(300.0);
                registroDeEstoque.getRepresentante().setNome("Joao");
                assertNotNull(registroDeEstoque.getId());
            }
        }
    }
}