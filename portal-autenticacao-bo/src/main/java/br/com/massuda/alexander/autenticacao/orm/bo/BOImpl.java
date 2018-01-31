package br.com.massuda.alexander.autenticacao.orm.bo;

import br.com.waiso.framework.abstratas.Classe;

public abstract class BOImpl<ChavePrimaria, T> extends Classe implements IBO<ChavePrimaria, T> {

	public String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
}
