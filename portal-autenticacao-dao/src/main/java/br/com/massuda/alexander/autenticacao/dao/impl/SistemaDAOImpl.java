package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.ISistemaDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Sistema;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class SistemaDAOImpl extends DAO<Sistema>
							implements ISistemaDAO {

	public SistemaDAOImpl() {
		super(Sistema.class);
	}
	
	public Sistema incluir(Sistema o) throws ErroUsuario {
		return super.incluir(o);
	}

	public void editar(Sistema o) throws ErroUsuario {
		super.editar(o);
	}

	public void excluir(Sistema o) throws ErroUsuario {
		super.excluir(o);
	}

}
