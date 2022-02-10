package com.mercadolivre.projetointegradow4g1.dto.extras;

import java.math.BigDecimal;

import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCarrinhoDTO {
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;
    private Double volumeUni;
    private CondicaoConservacao conservacao;
}
