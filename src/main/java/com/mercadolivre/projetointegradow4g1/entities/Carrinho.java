package com.mercadolivre.projetointegradow4g1.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal precoTotal;

    @OneToMany(mappedBy = "carrinho")
    private Set<CarrinhoAnuncio> carrinhoAnuncios;

    @OneToOne
    private Comprador comprador;

    @OneToOne
    private Compra compra;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carrinho)) return false;
        Carrinho carrinho = (Carrinho) o;
        return id.equals(carrinho.id) && carrinhoAnuncios.equals(carrinho.carrinhoAnuncios) && comprador.equals(carrinho.comprador) && compra.equals(carrinho.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrinhoAnuncios, comprador, compra);
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", carrinhoAnuncios=" + carrinhoAnuncios +
                ", comprador=" + comprador +
                ", compra=" + compra +
                '}';
    }
}
