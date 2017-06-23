package br.com.massuda.alexander.autenticacao.dto;

import java.util.Date;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;

public class UsuarioDTO extends Usuario {

	private Date dataCriacao;
	private Date dataNascimento;
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
