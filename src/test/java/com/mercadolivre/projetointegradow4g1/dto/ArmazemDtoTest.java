package com.mercadolivre.projetointegradow4g1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemDtoTest {

    @NotEmpty
    @NotNull
    private String nome, descricao;

    @JsonIgnore
    private Set<Setor> setores;

    @JsonIgnore
    private Set<Representante> representantes;

    public static Armazem converte(ArmazemDtoTest dto) {
        Armazem armazem = Armazem.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        return armazem;
    }

    public static ArmazemDtoTest converte(Armazem armazem) {
        return ArmazemDtoTest.builder()
                .nome(armazem.getNome())
                .descricao(armazem.getDescricao())
                .representantes(armazem.getRepresentantes())
                .setores(armazem.getSetores())
                .build();
    }

    public static List<ArmazemDtoTest> converte(List<Armazem> armazens) {
        return armazens.stream().map(a -> converte(a)).collect(Collectors.toList());
    }
}
