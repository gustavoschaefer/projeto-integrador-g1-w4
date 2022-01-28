package com.mercadolivre.projetointegradow4g1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Representante;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
