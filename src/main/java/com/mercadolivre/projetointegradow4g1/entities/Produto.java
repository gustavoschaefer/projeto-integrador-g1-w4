package com.mercadolivre.projetointegradow4g1.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mercadolivre.projetointegradow4g1.entities.enums.TipoDeSetor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
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
	private TipoDeSetor conservacao;


	@JsonIgnore
	@OneToOne
	private Anuncio anuncio;





}
