package com.mercadolivre.projetointegradow4g1.entities;


import lombok.*;

import javax.persistence.*;

@Table(name = "tb_anuncio")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @OneToOne
    private Lote lote;

    @ManyToOne
    private Vendedor vendedor;

}
