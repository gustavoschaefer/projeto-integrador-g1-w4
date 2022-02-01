package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private Long volume_uni;
    private CondicaoConservacao conservacao;

    public static Produto converte(ProdutoDTO dto){
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .volume_uni(dto.getVolume_uni())
                .conservacao(dto.getConservacao())
                .build();
        return produto;
    }

    public static ProdutoDTO converte(Produto produto){
        ProdutoDTO dto = ProdutoDTO.builder()
                .nome(produto.getNome())
                .volume_uni(produto.getVolume_uni())
                .conservacao(produto.getConservacao())
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
                ", volume_uni=" + volume_uni +
                ", conservacao=" + conservacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoDTO)) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return nome.equals(that.nome) && volume_uni.equals(that.volume_uni) && conservacao == that.conservacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, volume_uni, conservacao);
    }
}
