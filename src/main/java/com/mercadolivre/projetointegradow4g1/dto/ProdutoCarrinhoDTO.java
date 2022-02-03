package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
