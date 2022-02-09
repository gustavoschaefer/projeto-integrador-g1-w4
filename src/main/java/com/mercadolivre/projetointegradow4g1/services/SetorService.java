package com.mercadolivre.projetointegradow4g1.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;

@Service
public class SetorService {

	private static SetorRepository setorRepository;

	public SetorService(SetorRepository repository) {
		SetorService.setorRepository = repository;
	}

	public Setor salvar(Setor setor) {
		if (ArmazemService.existe(setor.getArmazem())) {
			setor.setCapacidadeAtual(0.0);
			return setorRepository.save(setor);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Armazem invalido.");
		}
	}

	public List<Setor> listar() {
		return setorRepository.findAll();
	}

	public Setor buscar(Long id) {
		return setorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID não encontrado"));
	}	

	public static boolean existe(Setor setor) {
		return setorRepository.findById(setor.getId()).isPresent();
	}

	public static boolean temCapacidade(Setor setor, double volume) {
		Optional<Setor> setorOpt = setorRepository.findById(setor.getId());
		if (setorOpt.isPresent()) {
			return setorOpt.get().getCapacidadeTotal() - setorOpt.get().getCapacidadeAtual() >= volume;
		}
		return false;
	}

	public static boolean confereTipo(Setor setor, CondicaoConservacao condicao) {
		Optional<Setor> setorOpt = setorRepository.findById(setor.getId());
		if (setorOpt.isPresent()) {
			return setorOpt.get().getTipo().equals(condicao);
		}
		return false;
	}

	public static void atualizaCapacidade(Setor setor, double volume) {
		Optional<Setor> setorOpt = setorRepository.findById(setor.getId());
		if (setorOpt.isPresent() && SetorService.temCapacidade(setor, volume)) {
			setorOpt.get().setCapacidadeAtual(setorOpt.get().getCapacidadeAtual() + volume);
		}
	}
	
	public List<SetorRepository.SetorTmp> buscaLostesPorSetor(Long id, Map<String, String> conservacao, Integer dias) {
		Instant data = Instant.now().plus(dias, ChronoUnit.DAYS);
		List<SetorRepository.SetorTmp> lotes = setorRepository.buscaLostesPorSetor(id, data);
		for (Map.Entry<String, String> entry : conservacao.entrySet()) {
			if (entry.getKey().equals("conservacao")) {
				if (entry.getValue().equals("FS")) {
					lotes = lotes.stream().filter(p -> p.getSetor().toString().equals("FRESCO"))
							.collect(Collectors.toList());
				}
				if (entry.getValue().equals("RF")) {
					lotes = lotes.stream().filter(p -> p.getSetor().toString().equals("RESFRIADO"))
							.collect(Collectors.toList());
				}
				if (entry.getValue().equals("FF")) {
					lotes = lotes.stream().filter(p -> p.getSetor().toString().equals("CONGELADO"))
							.collect(Collectors.toList());
				}
			}
		}
		if (lotes.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum lote encontrado.");
		}
		return lotes;
	}
}