package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IPerfilDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.Perfil;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class PerfilDAOImpl extends DAO<Perfil> implements IPerfilDAO {

	public PerfilDAOImpl() {
		super(Perfil.class);
	}

	@Override
	public Perfil incluir(Perfil o) throws ErroUsuario {
		return super.incluir(o);
	}

	@Override
	public void editar(Perfil o) throws ErroUsuario {
		super.editar(o);
	}

	@Override
	public void excluir(Perfil o) throws ErroUsuario {
		super.excluir(o);
	}

}
