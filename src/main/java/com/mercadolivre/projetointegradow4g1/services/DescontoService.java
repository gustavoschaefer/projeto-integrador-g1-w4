package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.projetointegradow4g1.entities.Desconto;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.DescontoRepository;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;

@Service
public class DescontoService {
	
	DescontoRepository descontoRepository;
	
	public DescontoService(DescontoRepository descontoRepository, VendedorRepository vendedorRepository) {
		this.descontoRepository = descontoRepository;		
	}
	
	public Desconto salvar(Vendedor vendedor, Map<Integer, Double> desconto) {
		
		if(VendedorService.existe(vendedor.getId())) {
			return descontoRepository.save(new Desconto(null, vendedor, desconto));			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O vendedor informado não existe.");
		}			
	}
	
	public List<Desconto> listar(){
		return descontoRepository.findAll();		
	}
}