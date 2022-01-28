package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.repositories.LoteRepository;

@Service
public class LoteService {

	private LoteRepository repository;

	public LoteService(LoteRepository repository) {
		this.repository = repository;
	}

	public void salvar(Lote lote) {
		this.repository.save(lote);
	}

	public List<Lote> listar() {
		return this.repository.findAll();
	}

	public Lote obter(Long id) {
		Optional<Lote> op = this.repository.findById(id);
		return op.orElse(new Lote());
	}
}