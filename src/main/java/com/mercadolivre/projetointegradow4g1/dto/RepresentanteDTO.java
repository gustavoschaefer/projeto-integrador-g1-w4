package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepresentanteDTO {

    @NotEmpty
    @NotNull
    private String nome;

    @NotNull
    private Armazem armazem;

    public static Representante converte(RepresentanteDTO dto) {
        Representante representante = Representante.builder()
                .nome(dto.getNome())
                .armazem(dto.getArmazem())
                .build();
        return representante;
    }

    public static RepresentanteDTO converte(Representante representante) {
        return RepresentanteDTO.builder()
                .nome(representante.getNome())
                .armazem(representante.getArmazem())
                .build();
    }

    public static List<RepresentanteDTO> converte(List<Representante> representantes) {
        return representantes.stream().map(r -> converte(r)).collect(Collectors.toList());
    }
}
