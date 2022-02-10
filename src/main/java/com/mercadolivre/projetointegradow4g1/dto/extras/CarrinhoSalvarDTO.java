package com.mercadolivre.projetointegradow4g1.dto.extras;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoSalvarDTO {
    private BigDecimal precoTotal;
}
