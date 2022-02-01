package com.mercadolivre.projetointegradow4g1.dto;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO {

    @NotNull
    @NotEmpty(message = "O titulo do Anuncio é obrigatório.")
    private String titulo;

    @NotNull
    @NotEmpty(message = "A descrição do Anuncio é obrigatório.")
    private String descricao;
    private Lote lote;
    private Vendedor vendedor;
    @NotNull
    private BigDecimal preco;

    public static Anuncio converte(AnuncioDTO dto){
        Anuncio anuncio = Anuncio.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .lote(dto.getLote())
                .vendedor(dto.getVendedor())
                .preco(dto.getPreco())
                .build();
        return anuncio;
    }

    public static AnuncioDTO converte(Anuncio anuncio){
        return AnuncioDTO.builder()
                .titulo(anuncio.getTitulo())
                .descricao(anuncio.getDescricao())
                .lote(anuncio.getLote())
                .vendedor(anuncio.getVendedor())
                .preco(anuncio.getPreco())
                .build();
    }

    public static List<AnuncioDTO> converte(List<Anuncio> anuncios){
        return anuncios.stream().map(AnuncioDTO::converte).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "AnuncioDTO{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", lote=" + lote +
                ", vendedor=" + vendedor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioDTO that = (AnuncioDTO) o;
        return titulo.equals(that.titulo) && descricao.equals(that.descricao) && lote.equals(that.lote) && vendedor.equals(that.vendedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, lote, vendedor);
    }
}
