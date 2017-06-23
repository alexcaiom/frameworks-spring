package br.com.massuda.alexander.autenticacao.orm.bo;

import br.com.waiso.framework.abstratas.Classe;

public abstract class BOImpl<T> extends Classe implements IBO<T> {

	public String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
}
