package com.algaworks.comercial.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OportunidadeDTO {
	
	private String nomeProspecto;
	
	private String descricao;
	
	private BigDecimal valor;

}
