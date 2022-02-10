package com.mercadolivre.projetointegradow4g1.services.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.mercadolivre.projetointegradow4g1.dto.extras.ArmazemProdDTO;
import com.mercadolivre.projetointegradow4g1.dto.extras.ProdutoArmazemDTO;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository.ArmazemTmp;
import com.mercadolivre.projetointegradow4g1.services.ArmazemService;

public class ArmazemServiceTest {

    @Test
    void deveCadastrarArmazem() {
        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);

        Armazem armazem = Armazem.builder()
                .id(1L)
                .nome("Armazem 1")
                .descricao("Descrição Armazem 1")
                .build();

        Mockito.when(mock.save(Mockito.any(Armazem.class))).thenReturn(armazem);

        ArmazemService armazemService = new ArmazemService(mock);
        armazemService.salvar(armazem);
        assertNotNull(armazem.getId());
    }

    @Test
    void deveListarArmazens() {
        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);

        ArrayList<Armazem> lista = new ArrayList<>();
        Armazem armazem1 = Armazem.builder().id(1L).nome("Armazem 1").descricao("Descrição Armazem 1").build();
        Armazem armazem2 = Armazem.builder().id(2L).nome("Armazem 2").descricao("Descrição Armazem 2").build();
        lista.add(armazem1);
        lista.add(armazem2);

        Mockito.when(mock.findAll()).thenReturn(lista);

        ArmazemService armazemService = new ArmazemService(mock);
        armazemService.listar();

        assertEquals(2, lista.size());
        assertEquals("Armazem 1", lista.get(0).getNome());
        assertEquals("Armazem 2", lista.get(1).getNome());
    }

    @Test
    void deveBuscarArmazem() {
        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);

        Optional<Armazem> optionalArmazem = Optional.of(Armazem.builder().id(1L).nome("Armazem 1").descricao("Descrição Armazem 1").build());

        Mockito.when(mock.findById(1L)).thenReturn(optionalArmazem);

        ArmazemService armazemService = new ArmazemService(mock);
        armazemService.buscar(1L);

        assertEquals(1L, optionalArmazem.get().getId());
    }

    @Test
    void deveExistirArmazem() {
        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);

        Armazem armazem = Armazem.builder()
                .id(15L)
                .nome("Armazem 15")
                .descricao("Descrição Armazem 15")
                .build();
        Optional<Armazem> optionalArmazem = Optional.of(armazem);

        Mockito.when(mock.findById(15L)).thenReturn(optionalArmazem);

        new ArmazemService(mock);
        assertTrue(ArmazemService.existe(armazem));
    }

    @Test
    void deveExistirProdutoPorArmazem() {
        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);
        ArmazemTmp armazemTmp = new ArmazemTmp() {
            @Override
            public Integer getArmazem() { return 1; }
            @Override
            public Integer getQuantidade() { return 20; }
            @Override
            public String getNome() { return "Carne"; }
        };
        List<ArmazemTmp> armazemTmpList = new ArrayList<>();
        armazemTmpList.add(armazemTmp);

        ProdutoArmazemDTO produtoArmazemDTO = ProdutoArmazemDTO.builder()
                .armazens(Arrays.asList(ArmazemProdDTO.builder() .armazemId(1).quantidade(20).build()))
                .nome("Carne")
                .build();

        Mockito.when(mock.buscaProdutoArmazem(Mockito.anyLong())).thenReturn(armazemTmpList);

        ArmazemService armazemService = new ArmazemService(mock);
        armazemService.buscaProdutoPorArmazem(1L);

        try(MockedStatic<ProdutoArmazemDTO> produtoArmazemDTOMockedStatic = Mockito.mockStatic(ProdutoArmazemDTO.class)) {
            produtoArmazemDTOMockedStatic.when(
                    () -> ProdutoArmazemDTO.converte(Mockito.anyList())
            ).thenReturn(produtoArmazemDTO);

            assertEquals(armazemService.buscaProdutoPorArmazem(1L),produtoArmazemDTO);
        }
    }
}