package com.mercadolivre.projetointegradow4g1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	private Long volume_uni;
	private CondicaoConservacao conservacao;

	@OneToMany(mappedBy = "produto")
	private Set<Lote> lotes;


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
