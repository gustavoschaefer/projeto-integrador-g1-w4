package com.mercadolivre.projetointegradow4g1.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.mercadolivre.projetointegradow4g1.entities.Desconto;
import com.mercadolivre.projetointegradow4g1.entities.Vendedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescontoDTO {
	
	private Vendedor vendedor;
	private Integer quantidade;
	private Double porcentagem;
	
	public static Desconto converte(DescontoDTO dto) {
		Desconto desconto = Desconto.builder()
				.vendedor(dto.getVendedor())
				.quantidade(dto.getQuantidade())
				.porcentagem(dto.getPorcentagem())
				.build();
		return desconto;
	}
	
	public static DescontoDTO converte(Desconto desconto) {
		DescontoDTO dto = DescontoDTO.builder()
				.vendedor(desconto.getVendedor())
				.quantidade(desconto.getQuantidade())
				.porcentagem(desconto.getPorcentagem())
				.build();
		return dto;
	}
	
	public static List<DescontoDTO> converte(List<Desconto> descontos){
		return descontos.stream().map(d -> converte(d)).collect(Collectors.toList());		
	}
}