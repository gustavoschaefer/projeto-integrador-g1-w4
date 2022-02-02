package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetorDTO {
	
	@NotEmpty
	private String nome;
	
	@NotNull
	private Armazem armazem;
	
	@NotEmpty
	private Double capacidadeTotal;
	@NotNull
	private CondicaoConservacao tipo;
	
	public static Setor converte(SetorDTO dto) {
		Setor setor = Setor.builder()
				.nome(dto.getNome())
				.armazem(dto.getArmazem())
				.capacidadeTotal(dto.getCapacidadeTotal())
				.tipo(dto.getTipo())
				.build();
		return setor;
	}
	
	public static SetorDTO converte(Setor setor) {
		return SetorDTO.builder()
				.nome(setor.getNome())
				.armazem(setor.getArmazem())
				.capacidadeTotal(setor.getCapacidadeTotal())
				.tipo(setor.getTipo())
				.build();
	}
	
	public static List<SetorDTO> converte(List<Setor> setores){
		return setores.stream().map(s -> converte(s)).collect(Collectors.toList());		
	}
}
