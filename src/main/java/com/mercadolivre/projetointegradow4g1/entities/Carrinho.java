package com.mercadolivre.projetointegradow4g1.entities;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<CarrinhoAnuncio> carrinhoAnuncios;

    @OneToOne
    private Comprador comprador;

    @OneToOne
    @JsonIgnore
    private Compra compra;

    BigDecimal valorFrete;

    BigDecimal valorDesconto;

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
