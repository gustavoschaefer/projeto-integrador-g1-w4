package com.mercadolivre.projetointegradow4g1.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant data;

    @OneToOne
    private Carrinho carrinho;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra compra = (Compra) o;
        return id.equals(compra.id) && data.equals(compra.data) && carrinho.equals(compra.carrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, carrinho);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", data=" + data +
                ", carrinho=" + carrinho +
                '}';
    }
}
