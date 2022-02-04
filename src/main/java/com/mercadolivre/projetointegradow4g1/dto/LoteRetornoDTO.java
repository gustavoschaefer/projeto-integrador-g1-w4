package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository.ProdutoTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteRetornoDTO {
    Integer loteId;
    Integer quantidade;
    Instant validade;

    public static LoteRetornoDTO converte(ProdutoTmp produtoTmp) {
        return LoteRetornoDTO.builder()
                .loteId(produtoTmp.getLote())
                .quantidade(produtoTmp.getQuantidade())
                .validade(produtoTmp.getValidade())
                .build();
    }

    public static List<LoteRetornoDTO> converte(List<ProdutoTmp> produtoTmps) {
        return produtoTmps.stream().map(a -> converte(a)).collect(Collectors.toList());
    }
}
