package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository;
import com.mercadolivre.projetointegradow4g1.repositories.RepresentanteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepresentanteServiceTest {
//    @Test
//    void deveCadastrarRepresentante() {
//        RepresentanteRepository mock = Mockito.mock(RepresentanteRepository.class);
//
//        Representante representante = Representante.builder()
//            .id(1L)
//            .nome("Representante 1")
//            .armazem(
//                Armazem.builder()
//                    .id(1L)
//                    .nome("Armazem 1")
//                    .descricao("Descrição Armazem 1")
//                    .build()
//            )
//            .build();
//
//        Mockito.when(mock.save(Mockito.any(Representante.class))).thenReturn(representante);
//        Mockito.when(ArmazemService.existe(Mockito.any(Armazem.class))).thenReturn(true);
//
//        RepresentanteService representanteService = new RepresentanteService(mock);
//        representanteService.salvar(representante);
//        assertNotNull(representante.getId());
//    }

    @Test
    void deveListarRepresentantes() {
        RepresentanteRepository mock = Mockito.mock(RepresentanteRepository.class);

        ArrayList<Representante> lista = new ArrayList<>();
        Representante representante1 = Representante.builder()
                .id(1L)
                .nome("Representante 1")
                .armazem(Armazem.builder().id(1L).nome("Armazem 1").descricao("Descrição Armazem 1").build())
                .build();
        Representante representante2 = Representante.builder()
                .id(2L)
                .nome("Representante 2")
                .armazem(Armazem.builder().id(2L).nome("Armazem 2").descricao("Descrição Armazem 2").build())
                .build();
        lista.add(representante1);
        lista.add(representante2);

        Mockito.when(mock.findAll()).thenReturn(lista);

        RepresentanteService representanteService = new RepresentanteService(mock);
        representanteService.listar();

        assertEquals(2, lista.size());
        assertEquals("Representante 1", lista.get(0).getNome());
        assertEquals("Representante 2", lista.get(1).getNome());
    }

    @Test
    void deveBuscarRepresentante() {
        RepresentanteRepository mock = Mockito.mock(RepresentanteRepository.class);

        Optional<Representante> optionalRepresentante = Optional.of(Representante.builder()
                .id(1L)
                .nome("Representante 1")
                .armazem(Armazem.builder().id(1L).nome("Armazem 1").descricao("Descrição Armazem 1").build())
                .build());

        Mockito.when(mock.findById(1L)).thenReturn(optionalRepresentante);

        RepresentanteService representanteService = new RepresentanteService(mock);
        representanteService.buscar(1L);

        assertEquals(1L, optionalRepresentante.get().getId());
    }

}
