package br.com.massuda.alexander.autenticacao.orm.bo;

import br.com.massuda.alexander.autenticacao.excecoes.Erro;

/**
 * @author Alex
 *
 */
public interface IBO<T> {

	/**
	 * Metodos de Exclusao sao preparados para realizar exclusao de Listas<br/>
	 * de Objetos
	 * @param o
	 * @throws Erro
	 */
	public abstract void excluir(T o) throws Erro;

}