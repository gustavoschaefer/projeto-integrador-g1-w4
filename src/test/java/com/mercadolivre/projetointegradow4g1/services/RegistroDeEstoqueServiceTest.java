/*
package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.Instant;
import java.util.Set;

public class RegistroDeEstoqueServiceTest {

    @Test
    public void deveCadastrarRegistroDeEstoque(){
        RegistroDeEstoqueRepository mock = Mockito.mock(RegistroDeEstoqueRepository.class);

        RegistroDeEstoque registroDeEstoque = RegistroDeEstoque.builder()
                .id(1L)
                .data(Instant.parse("2018-05-07T15:20:45.765Z"))
                .setor(Setor.builder().build())
                .lotes()
                .representante(Representante.builder().build())
                .build();

        Mockito.when(mock.save(Mockito.any(RegistroDeEstoque.class))).thenReturn(registroDeEstoque);

        RegistroDeEstoqueService registroDeEstoqueService = new RegistroDeEstoqueService(mock);
        registroDeEstoqueService.salvarRegistroDeEstoque(registroDeEstoque);
        assertNotNull(registroDeEstoque.getId());


    }
}*/
