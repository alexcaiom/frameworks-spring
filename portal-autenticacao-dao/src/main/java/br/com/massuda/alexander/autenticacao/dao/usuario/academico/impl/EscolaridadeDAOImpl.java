package br.com.massuda.alexander.autenticacao.dao.usuario.academico.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.impl.DAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.IEscolaridadeDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.Escolaridade;

@Component
public class EscolaridadeDAOImpl extends DAO<Escolaridade> implements IEscolaridadeDAO {

	public EscolaridadeDAOImpl() {
		super(Escolaridade.class);
	}

}
