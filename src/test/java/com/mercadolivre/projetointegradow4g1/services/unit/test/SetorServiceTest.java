package com.mercadolivre.projetointegradow4g1.services.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import com.mercadolivre.projetointegradow4g1.services.ArmazemService;
import com.mercadolivre.projetointegradow4g1.services.SetorService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;

public class SetorServiceTest {

	@Test
	void deveCadastrarSetor() {

		SetorRepository mock = Mockito.mock(SetorRepository.class);

		Armazem armazem = Armazem.builder()
				.id(1L)
				.nome("Armazem 1")
				.descricao("Descrição Armazem 1")
				.build();

		Setor setor = Setor.builder()
				.id(1L)
				.nome("Setor Congelados")
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(armazem)
				.build();

		Mockito.when(mock.save(Mockito.any(Setor.class))).thenReturn(setor);

		SetorService setorService = new SetorService(mock);

		try (MockedStatic<ArmazemService> armazemServiceMockedStatic = Mockito.mockStatic(ArmazemService.class)) {
			armazemServiceMockedStatic.when(() -> ArmazemService.existe(Mockito.any(Armazem.class))).thenReturn(true);
			setorService.salvar(setor);
		}
		assertNotNull(setor.getId());
	}

	@Test
	void deveListarSetores() {
		SetorRepository mock = Mockito.mock(SetorRepository.class);

		ArrayList<Setor> lista = new ArrayList<>();

		Armazem armazem = Armazem.builder()
				.id(1L)
				.nome("Armazem 1")
				.descricao("Descrição Armazem 1")
				.build();

		Setor setor1 = Setor.builder()
				.id(1L)
				.nome("Setor Congelados")
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(armazem)
				.build();

		Setor setor2 = Setor.builder().id(2L).nome("Setor Resfriados").capacidadeTotal(150.00)
				.tipo(CondicaoConservacao.RESFRIADO).armazem(armazem).build();

		lista.add(setor1);
		lista.add(setor2);

		Mockito.when(mock.findAll()).thenReturn(lista);

		SetorService setorService = new SetorService(mock);
		setorService.listar();

		assertEquals(2, lista.size());
		assertEquals("Setor Congelados", lista.get(0).getNome());
		assertEquals("Setor Resfriados", lista.get(1).getNome());
	}

	@Test
	void deveBuscarSetor() {
		SetorRepository mock = Mockito.mock(SetorRepository.class);

		Optional<Setor> setorOpt = Optional.of(Setor.builder().id(1L).nome("Setor Resfriados").capacidadeTotal(150.00)
				.tipo(CondicaoConservacao.RESFRIADO)
				.armazem(Armazem.builder().id(1L).nome("Armazem 1").descricao("Descrição Armazem 1").build()).build());

		Mockito.when(mock.findById(1L)).thenReturn(setorOpt);

		SetorService setorService = new SetorService(mock);
		setorService.buscar(1L);

		assertEquals(1L, setorOpt.get().getId());
	}
	
	@Test
    void deveExistirSetor() {
        SetorRepository mock = Mockito.mock(SetorRepository.class);

        Setor setor = Setor.builder()
        		.id(1L)
				.nome("Setor Congelados")
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(Armazem.builder()
						.id(1L)
						.nome("Armazem 1")
						.descricao("Descrição Armazem 1")
						.build())
				.build();
        
        Optional<Setor> optionalSetor = Optional.of(setor);

        Mockito.when(mock.findById(1L)).thenReturn(optionalSetor);

        new SetorService(mock);
        assertTrue(SetorService.existe(setor));
    }
	
	@Test
	void deveTerCapacidade() {
		SetorRepository mock = Mockito.mock(SetorRepository.class);

        Setor setor = Setor.builder()
        		.id(1L)
				.nome("Setor Congelados")
				.capacidadeAtual(00.00)
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(Armazem.builder()
						.id(1L)
						.nome("Armazem 1")
						.descricao("Descrição Armazem 1")
						.build())
				.build();
        
        Optional<Setor> optionalSetor = Optional.of(setor);
        Mockito.when(mock.findById(1L)).thenReturn(optionalSetor);
        new SetorService(mock);
        assertTrue(SetorService.temCapacidade(optionalSetor.get(), 40.0));
	}
	
	@Test
	void deveSerDoMesmoTipo() {
		SetorRepository mock = Mockito.mock(SetorRepository.class);

		Setor setor = Setor.builder().
				id(1L)
				.nome("Setor Congelados")
				.capacidadeAtual(00.00)
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(Armazem.builder()
						.id(1L)
						.nome("Armazem 1")
						.descricao("Descrição Armazem 1")
						.build())
				.build();

		Optional<Setor> optionalSetor = Optional.of(setor);
		Mockito.when(mock.findById(1L)).thenReturn(optionalSetor);
		new SetorService(mock);
		assertTrue(SetorService.confereTipo(optionalSetor.get(), CondicaoConservacao.CONGELADO));
		
	}
	
	@Test
	void deveAtualizarCapacidade() {
		SetorRepository mock = Mockito.mock(SetorRepository.class);

		Setor setor = Setor.builder().
				id(1L)
				.nome("Setor Congelados")
				.capacidadeAtual(00.00)
				.capacidadeTotal(100.00)
				.tipo(CondicaoConservacao.CONGELADO)
				.armazem(Armazem.builder()
						.id(1L)
						.nome("Armazem 1")
						.descricao("Descrição Armazem 1")
						.build())
				.build();

		Optional<Setor> optionalSetor = Optional.of(setor);
		Mockito.when(mock.findById(1L)).thenReturn(optionalSetor);
		new SetorService(mock);
		SetorService.atualizaCapacidade(optionalSetor.get(), 40.0);
		
		assertEquals(40, optionalSetor.get().getCapacidadeAtual());
	}
	
	@Test
	void deveBuscarLotesNoSetor() {
		//TODO		
	}
}