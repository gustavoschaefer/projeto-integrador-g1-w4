package com.mercadolivre.projetointegradow4g1.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.LoteRepository;

public class LoteServiceTest {
	
	@Test
	void deveCadastrarLote() {
		LoteRepository mock = Mockito.mock(LoteRepository.class);
		
		Lote lote = Lote.builder()
				.id(1L)
				.quantidadeInicial(100)
				.quantidadeAtual(25)
				.dataFabricacao(Instant.now())
				.dataValidade(Instant.now().plus(90L, ChronoUnit.DAYS))
				.temperaturaMinima(5.0)
				.temperaturaAtual(3.0)				
				.build();
		
		Mockito.when(mock.save(Mockito.any(Lote.class))).thenReturn(lote);
		LoteService loteService = new LoteService(mock);
		assertNotNull(loteService.salvar(lote).getId());
	}
}