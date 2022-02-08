package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository.ArmazemTmp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        ArmazemService armazemService = new ArmazemService(mock);
        assertTrue(ArmazemService.existe(armazem));
    }

    //TODO Revisar como testar
//    @Test
//    void deveBusarProdutoPorArmazem() {
//        ArmazemRepository mock = Mockito.mock(ArmazemRepository.class);
//        ArmazemTmp mockArmazemTmp = Mockito.mock(ArmazemTmp.class);
//
//        Mockito.when(mockArmazemTmp.getArmazem()).thenReturn(1);
//        Mockito.when(mockArmazemTmp.getQuantidade()).thenReturn(20);
//        Mockito.when(mockArmazemTmp.getNome()).thenReturn("Carne");
//
//        ArmazemTmp armazemTmp = { 1, 20, "Carne" };
//        List<ArmazemTmp> armazemTmps = new ArrayList<ArmazemTmp>();
//        armazemTmps.
//
//        Mockito.when(mock.buscaProdutoArmazem(1L)).thenReturn(armazemTmps);
//
//        ArmazemService armazemService = new ArmazemService(mock);
//        assertTrue(ArmazemService.existe(armazem));
//    }

}
