package com.mercadolivre.projetointegradow4g1.entities;

import java.time.Instant;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_estoque")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDeEstoque {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant data;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Setor setor;

	@OneToMany(mappedBy = "registroDeEstoque",cascade = CascadeType.PERSIST)
	private Set<Lote> lotes;

}
