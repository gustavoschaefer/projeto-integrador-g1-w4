package com.mercadolivre.projetointegradow4g1.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.projetointegradow4g1.entities.Desconto;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.DescontoRepository;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;

@Service
public class DescontoService {

	private static DescontoRepository descontoRepository;

	public DescontoService(DescontoRepository descontoRepository, VendedorRepository vendedorRepository) {
		DescontoService.descontoRepository = descontoRepository;
	}

	public Desconto salvar(Vendedor vendedor, int quantidade, double porcentagem) {

		if (VendedorService.existe(vendedor.getId())) {
			return descontoRepository.save(new Desconto(null, quantidade, porcentagem, vendedor));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O vendedor informado não existe.");
		}
	}

	public List<Desconto> listar() {
		return descontoRepository.findAll();
	}
	
	public Desconto buscar(Long id) {
		return descontoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Desconto não cadastrado."));
	}

	public static Map<Integer, Double> buscaDescontosVendedor(Vendedor vendedor) {
		if (VendedorService.existe(vendedor.getId())) {
			List<Desconto> lista = descontoRepository.findAll()
					.stream()
					.filter(d-> d.getVendedor().getId().equals(vendedor.getId()))
					.collect(Collectors.toList());
			Map<Integer, Double> descontos = new HashMap<>();
			for (Desconto desconto : lista) {
				descontos.put(desconto.getQuantidade(), desconto.getPorcentagem());				
			}
			return descontos;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O vendedor informado não existe.");
		}

	}
}