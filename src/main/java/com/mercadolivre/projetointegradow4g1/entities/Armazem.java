package com.mercadolivre.projetointegradow4g1.entities;

import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tb_armazem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Armazem {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotEmpty
	@NotNull
	private String nome, descricao;

	@OneToMany(mappedBy = "armazem")
	@JsonIgnore
	private Set<Setor> setores;

	@OneToMany(mappedBy = "armazem")
	@JsonIgnore
	private Set<Representante> representantes;

}
