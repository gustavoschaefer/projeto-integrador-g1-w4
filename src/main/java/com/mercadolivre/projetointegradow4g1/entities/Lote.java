package com.mercadolivre.projetointegradow4g1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private RegistroDeEstoque registroDeEstoque;

    @OneToOne
    private Anuncio anuncio;
}