package br.com.apicl.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoSaidaDto {
	
	private String cep;
	private String logradouro;
	private String localidade;
	private String uf;
	

}
