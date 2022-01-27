package com.mercadolivre.projetointegradow4g1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;

@Repository
public interface RegistroDeEstoqueRepository extends JpaRepository<RegistroDeEstoque, Long>{

}