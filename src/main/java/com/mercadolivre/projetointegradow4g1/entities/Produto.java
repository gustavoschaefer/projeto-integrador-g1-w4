package com.mercadolivre.projetointegradow4g1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty(message = "O nome do Produto é obrigatório.")
	private String nome;
	@NotNull
	private Double volumeUni;
	private CondicaoConservacao conservacao;

	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<Lote> lotes;




	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto)) return false;
		Produto produto = (Produto) o;
		return getId().equals(produto.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
