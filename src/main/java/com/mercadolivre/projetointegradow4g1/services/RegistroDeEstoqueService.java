package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import com.mercadolivre.projetointegradow4g1.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroDeEstoqueService {

    @Autowired
    private RegistroDeEstoqueRepository registroDeEstoqueRepository;

    @Autowired
    private SetorRepository setorRepository;


    public ResponseEntity<RegistroDeEstoque> postRegistroDeEstoque(RegistroDeEstoque registroDeEstoque){

           try {
               registroDeEstoqueRepository.save(registroDeEstoque);
           }catch (Exception e){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
