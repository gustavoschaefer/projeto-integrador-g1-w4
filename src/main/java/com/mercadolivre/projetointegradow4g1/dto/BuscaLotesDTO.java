package com.mercadolivre.projetointegradow4g1.dto;



import java.util.List;

import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository.ProdutoTmp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaLotesDTO {
    Integer armazemId;
    Integer setorId;
    Integer produtoId;
    List<LoteRetornoDTO> lotes;

    public static BuscaLotesDTO converte(List<ProdutoTmp> produtoTmp){

        return BuscaLotesDTO.builder()
                .armazemId(produtoTmp.stream().findFirst().get().getArmazem())
                .setorId(produtoTmp.stream().findFirst().get().getSetor())
                .produtoId(produtoTmp.stream().findFirst().get().getProduto())
                .lotes(LoteRetornoDTO.converte(produtoTmp))
                .build();

    }




}
