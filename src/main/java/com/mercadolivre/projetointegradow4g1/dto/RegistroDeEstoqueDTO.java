package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDeEstoqueDTO {

    private Instant data;
    private Setor setor;
    private Set<Lote> lotes;
    private Representante representante;

    public static RegistroDeEstoque converte(RegistroDeEstoqueDTO dto) {
        RegistroDeEstoque registroDeEstoque = RegistroDeEstoque.builder()
                .data(dto.getData())
                .setor(dto.getSetor())
                .lotes(dto.getLotes())
                .representante(dto.getRepresentante())
                .build();
        return registroDeEstoque;
    }

    public static RegistroDeEstoqueDTO converte(RegistroDeEstoque registroDeEstoque) {
        RegistroDeEstoqueDTO dto = RegistroDeEstoqueDTO.builder()
                .data(registroDeEstoque.getData())
                .setor(registroDeEstoque.getSetor())
                .lotes(registroDeEstoque.getLotes())
                .representante(registroDeEstoque.getRepresentante())
                .build();
        return dto;
    }

    public static List<RegistroDeEstoqueDTO> converte(List<RegistroDeEstoque> registroDeEstoque) {
        return registroDeEstoque.stream().map(r -> converte(r)).collect(Collectors.toList());
    }

}
