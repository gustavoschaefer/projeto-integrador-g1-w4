package com.mercadolivre.projetointegradow4g1.entities;


import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_lote")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer quantidadeInicial;
    
    @Column(nullable = false)
    private Integer quantidadeAtual;    
    
    private Instant dataFabricacao;             
    private Instant dataValidade;
    private Double temperaturaAtual;
    private Double temperaturaMinima;    

    @Transient // TODO retirar após integrar com anúncio
    @OneToOne
    private Anuncio anuncio;

    @ManyToOne
    private RegistroDeEstoque registroDeEstoque;
}