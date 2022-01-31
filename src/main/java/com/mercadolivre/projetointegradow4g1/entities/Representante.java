package com.mercadolivre.projetointegradow4g1.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="tb_representante")
@Data
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

}
