package com.mercadolivre.projetointegradow4g1.dto;


import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaLotesDTO {
    Setor setor;
    Produto produto;
    List<Lote> lotes;
    Integer quantidade;
    Instant validade;

//    public static BuscaLotesDTO converte(ProdutoTmp produtoTmp){
//        return BuscaLotesDTO.builder()
//                .setor(produtoTmp.getSetor())
//                .produto(produtoTmp.getProduto())
//                .lotes(produtoTmp.getLote())
//                .quantidade(produtoTmp.getQuantidade())
//                .validade(produtoTmp.getValidade())
//                .build();
//
//    }

//    public static List<BuscaLotesDTO> converte(List<ProdutoTmp> produtoTmps) {
//        return produtoTmps.stream().map(a -> converte(a)).collect(Collectors.toList());
//    }


}
