package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.repositories.ArmazemRepository.ArmazemTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemProdDTO {

    Integer armazemId;
    Integer quantidade;

    public static ArmazemProdDTO converte(ArmazemTmp armazemTmp) {
        return ArmazemProdDTO.builder()
                .armazemId(armazemTmp.getArmazem())
                .quantidade(armazemTmp.getQuantidade())
                .build();
    }

    public static List<ArmazemProdDTO> converte(List<ArmazemTmp> armazemTmps) {
        return armazemTmps.stream().map(a -> converte(a)).collect(Collectors.toList());
    }
}
