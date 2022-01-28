package com.mercadolivre.projetointegradow4g1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class RepresentanteDTO {

//    @NotEmpty
//    @NotNull
//    private String nome;
//
//    @NotNull
//    @ManyToOne
//    private Armazem armazem;
//
//    public static Representante converte(RepresentanteDTO dto) {
//        Representante representante = Representante.builder()
//                .nome(dto.getNome())
//                .build();
//        return armazem;
//    }
//
//    public static RepresentanteDTO converte(Armazem armazem) {
//        return RepresentanteDTO.builder()
//                .nome(armazem.getNome())
//                .descricao(armazem.getDescricao())
//                .representantes(armazem.getRepresentantes())
//                .setores(armazem.getSetores())
//                .build();
//    }
//
//    public static List<RepresentanteDTO> converte(List<Armazem> armazens) {
//        return armazens.stream().map(a -> converte(a)).collect(Collectors.toList());
//    }
}
