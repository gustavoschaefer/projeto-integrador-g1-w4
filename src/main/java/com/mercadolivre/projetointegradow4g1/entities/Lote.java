package com.mercadolivre.projetointegradow4g1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_lote")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lote {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;	
	private Integer quantidade;
	
	@OneToOne
	private Produto produto;
		
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Pedido pedido;

}
