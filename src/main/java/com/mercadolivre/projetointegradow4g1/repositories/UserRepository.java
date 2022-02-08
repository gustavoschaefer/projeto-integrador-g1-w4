package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, String> {

    Usuario findByUser(String user);
}
