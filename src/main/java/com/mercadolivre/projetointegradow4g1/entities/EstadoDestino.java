package com.mercadolivre.projetointegradow4g1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "tb_estadoDestino")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class EstadoDestino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double distanciaOrigem;

    @OneToOne
    @JsonIgnore
    private Comprador comprador;

}
