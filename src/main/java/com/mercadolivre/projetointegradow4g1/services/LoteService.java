package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.LoteRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoteService {

	private static LoteRepository repository;

	public LoteService(LoteRepository repository) {
		LoteService.repository = repository;
	}

	public void salvar(Lote lote) {
		LoteService.repository.save(lote);
	}

	public List<Lote> listar() {
		return LoteService.repository.findAll();
	}

	public static Lote obter(Long id) {
		Optional<Lote> op = LoteService.repository.findById(id);
		return op.orElse(new Lote());
	}
	
	public static boolean existe(Lote lote) {
		return repository.findById(lote.getId()).isPresent();
	}

	public static void atualizaQuantidade(Lote lote, Integer quantidade) {
		Optional<Lote> loteOptional = repository.findById(lote.getId());
		if (loteOptional.isPresent()) {
			loteOptional.get().setQuantidadeAtual(loteOptional.get().getQuantidadeAtual() + quantidade);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lote invalido");
		}
	}
}