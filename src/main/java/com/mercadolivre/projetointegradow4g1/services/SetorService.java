package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;

@Service
public class SetorService {

	private SetorRepository repository;

	public SetorService(SetorRepository repository) {
		this.repository = repository;
	}

	public void salvar(Setor setor) {
		this.repository.save(setor);
	}

	public List<Setor> listar() {
		return this.repository.findAll();
	}

	public Setor obter(Long id) {
		Optional<Setor> op = this.repository.findById(id);
		return op.orElse(new Setor());
	}
}