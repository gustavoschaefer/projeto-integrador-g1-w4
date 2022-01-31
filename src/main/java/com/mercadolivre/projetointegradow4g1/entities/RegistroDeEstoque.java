package com.mercadolivre.projetointegradow4g1.entities;

import java.time.Instant;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Entity
@Table(name="tb_estoque")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDeEstoque {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant data;

	@ManyToOne
	private Setor setor;

	@ManyToMany
	private Set<Lote> lotes;

	@NotNull
	@ManyToOne
	private Representante representante;

}
