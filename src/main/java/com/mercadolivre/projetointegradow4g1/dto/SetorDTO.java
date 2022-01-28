package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.stream.Collectors;

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
	
	private String nome;
	private Armazem armazem;
	private CondicaoConservacao tipo;
	
	public static Setor converte(SetorDTO dto) {
		Setor setor = Setor.builder()
				.nome(dto.getNome())
				.armazem(dto.getArmazem())
				.tipo(dto.getTipo())
				.build();
		return setor;
	}
	
	public static SetorDTO converte(Setor setor) {
		return SetorDTO.builder()
				.nome(setor.getNome())
				.armazem(setor.getArmazem())
				.tipo(setor.getTipo())
				.build();
	}
	
	public static List<SetorDTO> converte(List<Setor> setores){
		return setores.stream().map(s -> converte(s)).collect(Collectors.toList());		
	}
}
