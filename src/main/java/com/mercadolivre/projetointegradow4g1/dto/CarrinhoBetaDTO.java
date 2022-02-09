package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoBetaDTO {

    private BigDecimal precoTotal;
    private BigDecimal valorFrete;
    private BigDecimal valorDesconto;

    public static Carrinho converte(CarrinhoBetaDTO dto) {
        return Carrinho.builder()
                .valorDesconto(dto.getValorDesconto())
                .valorFrete(dto.getValorFrete())
                .precoTotal(dto.getPrecoTotal())
                .build();
    }

    public static CarrinhoBetaDTO converte(Carrinho carrinho) {
        return CarrinhoBetaDTO.builder()
                .valorDesconto(carrinho.getValorDesconto())
                .valorFrete(carrinho.getValorFrete())
                .precoTotal(carrinho.getPrecoTotal())
                .build();
    }

}
