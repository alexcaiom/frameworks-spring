package br.com.massuda.alexander.autenticacao.dao.finder.usuario;

import java.util.List;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;

public interface IFinderUsuario extends IFinder<Long, Usuario> {
	
	public Usuario pesquisarPorLogin (String login);
	public List<Usuario> pesquisarPorLoginComo (String login);
	public List<Usuario> pesquisarPorNomeComo(String nome);
	
}
