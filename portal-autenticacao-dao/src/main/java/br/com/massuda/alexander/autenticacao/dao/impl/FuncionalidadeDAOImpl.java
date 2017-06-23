package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IFuncionalidadeDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Funcionalidade;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class FuncionalidadeDAOImpl extends DAO<Funcionalidade>
							implements IFuncionalidadeDAO {

	public FuncionalidadeDAOImpl() {
		super(Funcionalidade.class);
	}
	
	public Funcionalidade incluir(Funcionalidade o) throws ErroUsuario {
		return super.incluir(o);
	}

	public void editar(Funcionalidade o) throws ErroUsuario {
		super.editar(o);
	}

	public void excluir(Funcionalidade o) throws ErroUsuario {
		super.excluir(o);
	}

}
