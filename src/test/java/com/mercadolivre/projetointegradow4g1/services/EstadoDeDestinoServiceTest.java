package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.*;
import com.mercadolivre.projetointegradow4g1.repositories.EstadoDestinoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class EstadoDeDestinoServiceTest {

    @Test
    public void deveSalvarEstadoDeDestino(){
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);

        EstadoDestino estadoDestino = EstadoDestino.builder()
                .id(1L)
                .nome("São Paulo")
                .sigla("SP")
                .comprador(Comprador.builder().build())
                .build();

        Mockito.when(mock.save(Mockito.any(EstadoDestino.class))).thenReturn(estadoDestino);

        EstadoDestinoService estadoDestinoService = new EstadoDestinoService(mock);
        estadoDestinoService.salvar(estadoDestino);
        assertNotNull(estadoDestino.getId());
    }

    @Test
    public void deveSListarEstadoDeDestino(){
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        ArrayList<EstadoDestino> lista = new ArrayList<>();

        EstadoDestino estadoDestino = EstadoDestino.builder()
                .id(1L)
                .nome("São Paulo")
                .sigla("SP")
                .comprador(Comprador.builder().build())
                .build();

        EstadoDestino estadoDestino1 = EstadoDestino.builder()
                .id(2L)
                .nome("Rio de Janeiro")
                .sigla("RJ")
                .comprador(Comprador.builder().build())
                .build();

        lista.add(estadoDestino);
        lista.add(estadoDestino1);

        Mockito.when(mock.findAll()).thenReturn(lista);

        EstadoDestinoService estadoDestinoService = new EstadoDestinoService(mock);
        estadoDestinoService.listar();

        assertEquals(2, lista.size());
        assertEquals("São Paulo", lista.get(0).getNome());
        assertEquals("Rio de Janeiro", lista.get(1).getNome());
    }

}
