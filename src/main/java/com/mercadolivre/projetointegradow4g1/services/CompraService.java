package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.entities.Compra;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CompraRepository;

@Service
public class CompraService {
	
	private final CompraRepository compraRepository;
	private final CarrinhoRepository carrinhoRepo;
	
	public CompraService(CompraRepository compraRepository, CarrinhoRepository carrinhoRepo) {
		this.compraRepository = compraRepository;
		this.carrinhoRepo = carrinhoRepo;
	}
	
	public Compra salvar(Compra compra) {
		
		Optional<Carrinho> carrinhoOpt = carrinhoRepo.findById(compra.getCarrinho().getId());
		
		if(carrinhoOpt.isPresent()) {
			Compra novaCompra = this.compraRepository.save(compra);
			Carrinho carrinho = carrinhoOpt.get();
			carrinho.setCompra(novaCompra);
			this.carrinhoRepo.save(carrinho);
			return novaCompra;
		} else {
			throw new RuntimeException("Carrinho não existe");
		}
	}
	
	public List<Compra> listar(){
		return this.compraRepository.findAll();
	}

	public Compra buscar(Long id) {
		return this.compraRepository.findById(id).orElse(new Compra());		
	}
}
