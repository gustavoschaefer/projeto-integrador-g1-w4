package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;

@Service
public class SetorService {

	private static SetorRepository repository;

	public SetorService(SetorRepository repository) {
		SetorService.repository = repository;
	}

	public void salvar(Setor setor) {
		setor.setCapacidadeAtual(0.0);
		repository.save(setor);
	}

	public List<Setor> listar() {
		return repository.findAll();
	}

	public Setor obter(Long id) {
		Optional<Setor> op = repository.findById(id);
		return op.orElse(new Setor());
	}
	
	public static boolean existe(Setor setor) {
		return repository.findById(setor.getId()).isPresent();
	}
	
	public static boolean temCapacidade(Setor setor, double volume) {
		Optional<Setor> setorOpt = repository.findById(setor.getId());
		if(setorOpt.isPresent()) {
			return setorOpt.get().getCapacidadeTotal() - setorOpt.get().getCapacidadeAtual() >= volume;						
		}
		return false;
	}
	
	public static boolean confereTipo(Setor setor, CondicaoConservacao condicao) {
		Optional<Setor> setorOpt = repository.findById(setor.getId());
		if(setorOpt.isPresent()) {
			setorOpt.get().getTipo().equals(condicao);
		}
		return false;
	}
}