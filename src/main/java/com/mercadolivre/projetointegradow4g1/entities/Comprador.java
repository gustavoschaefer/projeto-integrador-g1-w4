package com.mercadolivre.projetointegradow4g1.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "tb_comprador")
public class Comprador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "O nome do Comprador é obrigatório")
    private String nome;

    @OneToOne
    @JsonIgnore
    private Carrinho carrinho;

    @OneToOne
    private EstadoDestino estadoDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comprador)) return false;
        Comprador comprador = (Comprador) o;
        return id.equals(comprador.id) && nome.equals(comprador.nome) && carrinho.equals(comprador.carrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, carrinho);
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", carrinho=" + carrinho +
                '}';
    }
}
