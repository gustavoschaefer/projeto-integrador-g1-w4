package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemDTO {

    @NotEmpty
    @NotNull
    private String nome, descricao;

    @JsonIgnore
    private Set<Setor> setores;

    @JsonIgnore
    private Set<Representante> representantes;

    public static Armazem converte(ArmazemDTO dto) {
        Armazem armazem = Armazem.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        return armazem;
    }

    public static ArmazemDTO converte(Armazem armazem) {
        return ArmazemDTO.builder()
                .nome(armazem.getNome())
                .descricao(armazem.getDescricao())
                .representantes(armazem.getRepresentantes())
                .setores(armazem.getSetores())
                .build();
    }

    public static List<ArmazemDTO> converte(List<Armazem> armazens) {
        return armazens.stream().map(a -> converte(a)).collect(Collectors.toList());
    }
}
