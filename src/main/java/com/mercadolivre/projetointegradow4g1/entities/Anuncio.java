package com.mercadolivre.projetointegradow4g1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tb_anuncio")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @OneToOne(cascade = CascadeType.MERGE)
    private Lote lote;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Vendedor vendedor;

}
