package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<CarrinhoBetaDTO> converte(List<Carrinho> carrinhos) {
        return carrinhos.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
}