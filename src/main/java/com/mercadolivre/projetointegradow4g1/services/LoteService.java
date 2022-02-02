package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.LoteRepository;

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

	public Lote obter(Long id) {
		Optional<Lote> op = LoteService.repository.findById(id);
		return op.orElse(new Lote());
	}
	
	public static boolean existe(Lote lote) {
		return repository.findById(lote.getId()).isPresent();
	}
}