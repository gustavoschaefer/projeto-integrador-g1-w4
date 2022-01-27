package com.mercadolivre.projetointegradow4g1.entities;

import java.time.Instant;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_pedido")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant data;
	@ManyToOne
	private Setor setor;

	@OneToMany(mappedBy = "pedido")
	private Set<Lote> lotes;

}
