package com.mercadolivre.projetointegradow4g1.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolivre.projetointegradow4g1.entities.Anuncio;
import com.mercadolivre.projetointegradow4g1.entities.Lote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteDTO {
	
	private Integer quantidadeInicial;
	private Integer quantidadeAtual;
	private Instant dataFabricacao;	
	private Instant dataValidade;
	private Double temperaturaAtual;
	private Double temperaturaMinima;
	private Anuncio anuncio;
	
	public static Lote converte(LoteDTO dto) {
		Lote lote = Lote.builder()
				.quantidadeInicial(dto.getQuantidadeInicial())
				.quantidadeAtual(dto.getQuantidadeAtual())
				.dataFabricacao(dto.getDataFabricacao())								
				.dataValidade(dto.getDataValidade())
				.temperaturaAtual(dto.getTemperaturaAtual())
				.temperaturaMinima(dto.getTemperaturaMinima())
				.anuncio(dto.getAnuncio())
				.build();
		return lote;
	}
	
	public static LoteDTO converte(Lote lote) {
		return LoteDTO.builder()
				.quantidadeInicial(lote.getQuantidadeInicial())
				.quantidadeAtual(lote.getQuantidadeAtual())
				.dataFabricacao(lote.getDataFabricacao())				
				.dataValidade(lote.getDataValidade())
				.temperaturaAtual(lote.getTemperaturaAtual())
				.temperaturaMinima(lote.getTemperaturaMinima())
				.anuncio(lote.getAnuncio())
				.build();
	}
	
	public static List<LoteDTO> converte(List<Lote> lotes){
		return lotes.stream().map(l -> converte(l)).collect(Collectors.toList());		
	}
}
