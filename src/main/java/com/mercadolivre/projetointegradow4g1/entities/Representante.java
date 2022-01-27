package com.mercadolivre.projetointegradow4g1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_representante")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Representante {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Armazem armazem;

}
