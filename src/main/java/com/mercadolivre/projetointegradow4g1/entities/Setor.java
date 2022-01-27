package com.mercadolivre.projetointegradow4g1.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mercadolivre.projetointegradow4g1.entities.enums.TipoDeSetor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_setor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setor {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private TipoDeSetor tipo;

	@ManyToOne
	@JoinColumn(name = "id_armazem", nullable = false)
	private Armazem armazem;

	@OneToMany(mappedBy = "setor")
	private Set<Pedido> pedidos;

}
