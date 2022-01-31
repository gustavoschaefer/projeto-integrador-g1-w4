package com.mercadolivre.projetointegradow4g1.dto;


import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
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
public class VendedorDTO {

    @NotNull
    @NotEmpty(message = "O nome do Produto é obrigatório.")
    private String nome;

    public static Vendedor converte(VendedorDTO dto){
        Vendedor vendedor = Vendedor.builder()
                .nome(dto.getNome())
                .build();
        return vendedor;
    }

    public static VendedorDTO converte(Vendedor vendedor){
        VendedorDTO dto = VendedorDTO.builder()
                .nome(vendedor.getNome())
                .build();
        return dto;
    }

    public static List<VendedorDTO> converte(List<Vendedor> vendedores) {
        return vendedores.stream().map(a -> converte(a)).collect(Collectors.toList());
    }
}
