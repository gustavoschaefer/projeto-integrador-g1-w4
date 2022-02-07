package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    @NotNull
    @NotEmpty(message = "O nome do Produto é obrigatório.")
    private String nome;
    @NotNull
    private Double volumeUni;
    private CondicaoConservacao conservacao;
    private List<Lote> lotes;

    public static Produto converte(ProdutoDTO dto){
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .volumeUni(dto.getVolumeUni())
                .conservacao(dto.getConservacao())
                .lotes(dto.getLotes())
                .build();
        return produto;
    }

    public static ProdutoDTO converte(Produto produto){
        ProdutoDTO dto = ProdutoDTO.builder()
                .nome(produto.getNome())
                .volumeUni(produto.getVolumeUni())
                .conservacao(produto.getConservacao())
                .lotes(produto.getLotes())
                .build();
        return dto;
    }

    public static List<ProdutoDTO> converte(List<Produto> produtos) {
        return produtos.stream().map(a -> converte(a)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "nome='" + nome + '\'' +
                ", volumeUni=" + volumeUni +
                ", conservacao=" + conservacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoDTO)) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return nome.equals(that.nome) && volumeUni.equals(that.volumeUni) && conservacao == that.conservacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, volumeUni, conservacao);
    }
}
