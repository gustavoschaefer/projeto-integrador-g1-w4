package com.mercadolivre.projetointegradow4g1.dto.extras;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoBuscarDTO {
    private List<ProdutoCarrinhoDTO> produtoCarrinhoDTOS;
    private BigDecimal precoTotal;
}