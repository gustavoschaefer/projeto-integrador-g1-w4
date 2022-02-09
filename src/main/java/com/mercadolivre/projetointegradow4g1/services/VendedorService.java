package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;

@Service
public class VendedorService {

    private static VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        VendedorService.vendedorRepository = vendedorRepository;
    }

    public void salvar(Vendedor vendedor){
        VendedorService.vendedorRepository.save(vendedor);
    }

    public List<Vendedor> listar(){
        return VendedorService.vendedorRepository.findAll();
    }
    
    public Vendedor buscar(Long id){
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.get();
    }
    
    public static boolean existe(Long id) {
    	return vendedorRepository.findById(id).isPresent();
    }
}