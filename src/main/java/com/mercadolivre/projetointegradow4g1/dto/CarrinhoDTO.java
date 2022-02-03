package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {
    private Comprador comprador;
    private List<CarrinhoAnuncio> carrinhoAnuncios;
    private BigDecimal precoTotal;

    public static Carrinho converte(CarrinhoDTO dto) {
        return Carrinho.builder()
                .comprador(dto.getComprador())
                .carrinhoAnuncios(dto.getCarrinhoAnuncios())
                .precoTotal(dto.getPrecoTotal())
                .build();
    }

    public static CarrinhoDTO converte(Carrinho carrinho) {
        return CarrinhoDTO.builder()
                .comprador(carrinho.getComprador())
                .carrinhoAnuncios(carrinho.getCarrinhoAnuncios())
                .precoTotal(carrinho.getPrecoTotal())
                .build();
    }

    public static CarrinhoSalvarDTO converteSalvar(Carrinho carrinho) {
        return CarrinhoSalvarDTO.builder()
                .precoTotal(carrinho.getPrecoTotal())
                .build();
    }

    public static CarrinhoBuscarDTO converteBuscar(Carrinho carrinho) {
        List<ProdutoCarrinhoDTO> produtoCarrinhoDTOS = new ArrayList<>();
        for (CarrinhoAnuncio carrinhoAnuncio :  carrinho.getCarrinhoAnuncios()) {
            ProdutoCarrinhoDTO produtoCarrinhoDTO = new ProdutoCarrinhoDTO();
            produtoCarrinhoDTO.setNome(carrinhoAnuncio.getAnuncio().getLote().getProduto().getNome());
            produtoCarrinhoDTO.setConservacao(carrinhoAnuncio.getAnuncio().getLote().getProduto().getConservacao());
            produtoCarrinhoDTO.setPreco(carrinhoAnuncio.getAnuncio().getPreco());
            produtoCarrinhoDTO.setQuantidade(carrinhoAnuncio.getQuantidade());
            produtoCarrinhoDTO.setVolumeUni(carrinhoAnuncio.getAnuncio().getLote().getProduto().getVolumeUni());
            produtoCarrinhoDTOS.add(produtoCarrinhoDTO);
        }

        return CarrinhoBuscarDTO.builder()
                .produtoCarrinhoDTOS(produtoCarrinhoDTOS)
                .precoTotal(carrinho.getPrecoTotal())
                .build();
    }

    public static List<CarrinhoDTO> converte(List<Carrinho> carrinhos) {
        return carrinhos.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
}
