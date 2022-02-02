package com.mercadolivre.projetointegradow4g1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_lote")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotNull    
    private Integer quantidadeInicial;
    
    @Column(nullable = false)
    @NotNull
    private Integer quantidadeAtual;
    
    @NotNull
    private Instant dataFabricacao;
    
    @NotNull
    private Instant dataValidade;
    
    @NotNull
    private Double temperaturaAtual;
    
    @NotNull
    private Double temperaturaMinima;

    @ManyToOne
    private Produto produto;

    @ManyToMany(mappedBy = "lotes")
    @JsonIgnore
    private Set<RegistroDeEstoque> registroDeEstoques;

    public double getVolumeTotal() {
        return quantidadeAtual * produto.getVolumeUni();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lote)) return false;
        Lote lote = (Lote) o;
        return getId().equals(lote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}