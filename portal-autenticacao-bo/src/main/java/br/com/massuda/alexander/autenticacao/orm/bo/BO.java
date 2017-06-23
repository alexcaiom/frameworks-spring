package br.com.massuda.alexander.autenticacao.orm.bo;

import br.com.waiso.framework.abstratas.Classe;

public class BO extends Classe {

	protected String getNomeEntidade(){
		int posicaoInicio = CLASSE_NOME.indexOf("BO") +2;
		int posicaoFim = CLASSE_NOME.indexOf("Impl");
		return CLASSE_NOME.substring(posicaoInicio, posicaoFim);
	}
	
}
