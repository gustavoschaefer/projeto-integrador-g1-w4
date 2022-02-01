package com.mercadolivre.projetointegradow4g1.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.entities.Compra;

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
public class CompraDTO {
	
	@NotNull
	@NotEmpty(message = "Data é obrigatória")
	private Instant data;
	@NotNull
	private Carrinho carrinho;
	
	public static Compra converte(CompraDTO dto) {
		Compra compra = Compra.builder()
				.data(dto.getData())
				.carrinho(dto.getCarrinho())
				.build();
		return compra;
	}
	
	public static CompraDTO converte(Compra compra) {
		CompraDTO dto = CompraDTO.builder()
				.data(compra.getData())
				.carrinho(compra.getCarrinho())
				.build();
		return dto;		
	}
	
	public static List<CompraDTO> converte(List<Compra> compras) {
        return compras.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
	
	

}
