package com.mercadolivre.projetointegradow4g1.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_representante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Representante {

  	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  	@NotEmpty
	@NotNull
	private String nome;

	@NotNull
	@ManyToOne
	private Armazem armazem;

	@OneToMany(mappedBy = "representante")
	@JsonIgnore
	private Set<RegistroDeEstoque> registroDeEstoques;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Representante)) return false;
		Representante that = (Representante) o;
		return getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
