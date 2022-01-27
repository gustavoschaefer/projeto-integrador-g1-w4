package com.mercadolivre.projetointegradow4g1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Integer quantidade;

    @OneToOne
    Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "id_registroDeEstoque", nullable = false)
    private RegistroDeEstoque registroDeEstoque;

}
