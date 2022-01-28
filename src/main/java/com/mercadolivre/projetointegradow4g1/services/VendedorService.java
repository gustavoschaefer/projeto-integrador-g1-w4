package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;

@Service
public class VendedorService {

    VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public void salvarVendedor(Vendedor vendedor){
        this.vendedorRepository.save(vendedor);
    }

    public List<Vendedor> listaVendedor(){
        return this.vendedorRepository.findAll();
    }
    public Vendedor buscarVendedor(Long id){
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.get();
    }
}
