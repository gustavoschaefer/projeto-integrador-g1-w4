package com.mercadolivre.projetointegradow4g1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VendedorService {

    VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public void salvar(Vendedor vendedor) {
        this.vendedorRepository.save(vendedor);
    }

    public Vendedor buscar(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendedor não cadastrado."));
    }

    public List<Vendedor> listar() {
        return this.vendedorRepository.findAll();
    }
}
