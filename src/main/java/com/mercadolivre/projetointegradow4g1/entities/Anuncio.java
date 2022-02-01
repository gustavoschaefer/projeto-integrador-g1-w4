package com.mercadolivre.projetointegradow4g1.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

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


    private BigDecimal preco;

    @OneToOne
    private Lote lote;

    @ManyToOne
    private Vendedor vendedor;

    @OneToMany(mappedBy = "anuncio")
    @JsonIgnore
    private Set<CarrinhoAnuncio> carrinhoAnuncios;

}
