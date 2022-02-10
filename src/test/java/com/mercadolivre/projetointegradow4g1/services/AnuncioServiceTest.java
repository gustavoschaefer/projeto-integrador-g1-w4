package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.AnuncioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnuncioServiceTest {

    @Test
    public void deveSalvarAnuncio(){
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
        anuncioService.salvar(anuncio);
        assertNotNull(anuncio.getId());

    }

    @Test
    public void deveListarAnuncios(){
        AnuncioRepository mock = Mockito.mock(AnuncioRepository.class);

        ArrayList<Anuncio> lista = new ArrayList<>();
        Anuncio anuncio1 = Anuncio.builder()
                .id(1L)
                .titulo("Anuncio 1")
                .descricao("Descrição anuncio 1")
                .preco(BigDecimal.valueOf(100.00))
                .lote(Lote.builder().build())
                .vendedor(Vendedor.builder().build())
                .build();

        Anuncio anuncio2 = Anuncio.builder()
                .id(2L)
                .titulo("Anuncio 2")
                .descricao("Descrição anuncio 2")
                .preco(BigDecimal.valueOf(200.00))
                .lote(Lote.builder().build())
                .vendedor(Vendedor.builder().build())
                .build();

        lista.add(anuncio1);
        lista.add(anuncio2);

        Mockito.when(mock.findAll()).thenReturn(lista);

        AnuncioService anuncioService = new AnuncioService(mock);
        anuncioService.listar();

        assertEquals(2, lista.size());
        assertEquals("Anuncio 1", lista.get(0).getTitulo());
        assertEquals("Anuncio 2", lista.get(1).getTitulo());
    }

    @Test
    public void deveBuscarAnuncio(){
        AnuncioRepository mock = Mockito.mock(AnuncioRepository.class);

        Anuncio anuncio = Anuncio.builder()
                .id(1L)
                .titulo("Anuncio 1")
                .descricao("Descrição anuncio 1")
                .preco(BigDecimal.valueOf(100.00))
                .lote(Lote.builder().build())
                .vendedor(Vendedor.builder().build())
                .build();

        Mockito.when(mock.findById(1L)).thenReturn(Optional.ofNullable(anuncio));

        AnuncioService anuncioService = new AnuncioService(mock);
        assertEquals(AnuncioService.buscar(1L), anuncio);

    }
}


