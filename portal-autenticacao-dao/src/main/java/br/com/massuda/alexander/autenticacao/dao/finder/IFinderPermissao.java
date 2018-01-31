package br.com.massuda.alexander.autenticacao.dao.finder;

import br.com.massuda.alexander.autenticacao.orm.modelo.Permissao;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;

public interface IFinderPermissao extends IFinder<Long, Permissao> {
	
	Permissao pesquisarPorUsuario (Long idUsuario);
	
}
