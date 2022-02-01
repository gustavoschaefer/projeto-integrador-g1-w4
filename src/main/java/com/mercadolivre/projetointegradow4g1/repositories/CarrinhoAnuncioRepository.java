package com.mercadolivre.projetointegradow4g1.repositories;


import com.mercadolivre.projetointegradow4g1.entities.CarrinhoAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoAnuncioRepository extends JpaRepository<CarrinhoAnuncio, Long> {
}
