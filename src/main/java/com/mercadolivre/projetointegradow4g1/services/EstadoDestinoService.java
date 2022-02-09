package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.dto.EstadoDestinoDTO;
import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import com.mercadolivre.projetointegradow4g1.repositories.EstadoDestinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoDestinoService {

    private static EstadoDestinoRepository estadoDestinoRepository;

    public EstadoDestinoService(EstadoDestinoRepository estadoDestinoRepository){
        EstadoDestinoService.estadoDestinoRepository = estadoDestinoRepository;
    }

    public EstadoDestino salvar(EstadoDestino estadoDestino){
        return estadoDestinoRepository.save(estadoDestino);
    }

    public List<EstadoDestino> listar() {
        return estadoDestinoRepository.findAll();
    }
}
