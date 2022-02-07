package com.mercadolivre.projetointegradow4g1.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;
import org.springframework.web.server.ResponseStatusException;

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

	public List<SetorRepository.SetorTmp> buscaLostesPorSetor(Long id, Map<String, String> conservacao, Integer dias){
		Instant data = Instant.now().plus(dias, ChronoUnit.DAYS);
		List< SetorRepository.SetorTmp > lotes = repository.buscaLostesPorSetor(id, data);
		for (Map.Entry<String, String> entry : conservacao.entrySet()) {
			if (entry.getKey().equals("conservacao")) {
				if (entry.getValue().equals("FS")) {
					lotes = lotes.stream()
							.filter(p -> p.getSetor().toString().equals("FRESCO")).collect(Collectors.toList());
				}
				if (entry.getValue().equals("RF")) {
					lotes = lotes.stream()
							.filter(p -> p.getSetor().toString().equals("RESFRIADO")).collect(Collectors.toList());
				}
				if (entry.getValue().equals("FF")) {
					lotes = lotes.stream()
							.filter(p -> p.getSetor().toString().equals("CONGELADO")).collect(Collectors.toList());
				}
			}
		}
		if (lotes.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum lote encontrado.");
		}
		return lotes;
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
			return setorOpt.get().getTipo().equals(condicao);
		}
		return false;
	}

	public static void atualizaCapacidade(Setor setor, double volume){
		Optional<Setor> setorOpt = repository.findById(setor.getId());
		if(setorOpt.isPresent()){
			setorOpt.get().setCapacidadeAtual(setorOpt.get().getCapacidadeAtual() + volume);
		}
	}
}