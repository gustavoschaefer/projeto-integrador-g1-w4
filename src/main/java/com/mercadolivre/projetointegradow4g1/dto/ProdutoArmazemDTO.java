package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository.ArmazemTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoArmazemDTO {
    String nome;
    List<ArmazemProdDTO> armazens;

    public static ProdutoArmazemDTO converte(List<ArmazemTmp> armazemTmps){
        return ProdutoArmazemDTO.builder()
                .nome(armazemTmps.stream().findFirst().get().getNome())
                .armazens(ArmazemProdDTO.converte(armazemTmps))
                .build();
    }

}