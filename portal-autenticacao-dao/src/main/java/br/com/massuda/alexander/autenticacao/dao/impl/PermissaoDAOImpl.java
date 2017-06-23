package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IPermissaoDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.Permissao;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class PermissaoDAOImpl extends DAO<Permissao>
							implements IPermissaoDAO {

	public PermissaoDAOImpl() {
		super(Permissao.class);
	}
	
	public Permissao incluir(Permissao o) throws ErroUsuario {
		return super.incluir(o);
	}

	public void editar(Permissao o) throws ErroUsuario {
		super.editar(o);
	}

	public void excluir(Permissao o) throws ErroUsuario {
		super.excluir(o);
	}

}
