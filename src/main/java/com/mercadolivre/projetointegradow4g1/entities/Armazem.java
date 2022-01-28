package com.mercadolivre.projetointegradow4g1.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String nome;
	private String descricao;
	
	@Transient //TODO retirar quando integrar com Setor
	@OneToMany(mappedBy = "armazem")
	private Set<Setor> setores;
	
	@OneToMany(mappedBy = "armazem")
	private Set<Representante> representantes;

}
