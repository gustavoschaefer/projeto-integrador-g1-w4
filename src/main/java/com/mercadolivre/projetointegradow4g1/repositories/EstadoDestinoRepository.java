package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoDestinoRepository extends JpaRepository<EstadoDestino, Long> {

}
