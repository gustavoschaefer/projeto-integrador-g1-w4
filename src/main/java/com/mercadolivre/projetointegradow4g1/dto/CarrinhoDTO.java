package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {
    private Comprador comprador;
    private Set<CarrinhoAnuncio> carrinhoAnuncios;
    private BigDecimal precoTotal;

    public static Carrinho converte(CarrinhoDTO dto) {
        Carrinho carrinho = Carrinho.builder()
                .comprador(dto.getComprador())
                .carrinhoAnuncios(dto.getCarrinhoAnuncios())
                .precoTotal(dto.getPrecoTotal())
                .build();
        return carrinho;
    }

    public static CarrinhoDTO converte(Carrinho carrinho) {
        CarrinhoDTO dto = CarrinhoDTO.builder()
                .comprador(carrinho.getComprador())
                .carrinhoAnuncios(carrinho.getCarrinhoAnuncios())
                .precoTotal(carrinho.getPrecoTotal())
                .build();
        return dto;
    }

    public static CarrinhoSalvarDTO converteSalvar(Carrinho carrinho) {
        CarrinhoSalvarDTO dto = CarrinhoSalvarDTO.builder()
                .precoTotal(carrinho.getPrecoTotal())
                .build();
        return dto;
    }

    public static List<CarrinhoDTO> converte(List<Carrinho> carrinhos) {
        return carrinhos.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
}
