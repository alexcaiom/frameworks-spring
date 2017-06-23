package br.com.massuda.alexander.autenticacao.dao.usuario.profissional.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.impl.DAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.ISegmentoDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Segmento;

@Component
public class SegmentoDAOImpl extends DAO<Segmento> implements ISegmentoDAO {

	public SegmentoDAOImpl() {
		super(Segmento.class);
	}

}
