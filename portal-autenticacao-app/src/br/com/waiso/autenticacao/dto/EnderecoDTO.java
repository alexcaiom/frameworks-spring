package br.com.waiso.autenticacao.dto;

import br.com.massuda.alexander.autenticacao.orm.modelo.Endereco;

public class EnderecoDTO extends Endereco {

	private String logradouro;
	private String cep;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
