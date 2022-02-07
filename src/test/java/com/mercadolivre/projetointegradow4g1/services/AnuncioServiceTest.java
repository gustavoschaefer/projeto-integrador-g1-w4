package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnuncioServiceTest {

    @Test
    public void deveCadastrarAnuncio(){
        AnuncioRepository mock = Mockito.mock(AnuncioRepository.class);

        Anuncio anuncio = Anuncio.builder()
                .id(1L)
                .titulo("Anuncio 1")
                .descricao("Descrição anuncio 1")
                .preco(BigDecimal.valueOf(100.00))
                .lote(Lote.builder().build())
                .vendedor(Vendedor.builder().build())
                .build();

        Mockito.when(mock.save(Mockito.any(Anuncio.class))).thenReturn(anuncio);

        AnuncioService anuncioService = new AnuncioService(mock);
        anuncioService.salvarAnuncio(anuncio);
        assertNotNull(anuncio.getId());

    }
}


