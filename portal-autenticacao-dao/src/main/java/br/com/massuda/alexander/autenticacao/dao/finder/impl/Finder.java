package br.com.massuda.alexander.autenticacao.dao.finder.impl;

import br.com.massuda.alexander.autenticacao.dao.impl.DAO;
import br.com.massuda.alexander.persistencia.jdbc.finder.FinderJDBC;

public abstract class Finder<T> extends FinderJDBC<T> {

	public Finder(Class<T> entidade) {
		super(entidade);
		DAO.configurarBancoDeDadosDadosAcesso();
	}

}
