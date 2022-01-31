package com.mercadolivre.projetointegradow4g1.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;

import lombok.*;

@Entity
@Table(name = "tb_setor")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setor {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private CondicaoConservacao tipo;

	@ManyToOne
	private Armazem armazem;
		
	@OneToMany(mappedBy = "setor")
	@JsonIgnore
	private Set<RegistroDeEstoque> registroDeEstoques;

}
