package br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.IFinderAtividadeEscolar;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.AtividadeEscolar;

@Component
public class FinderAtividadeEscolarImpl extends Finder<AtividadeEscolar> implements IFinderAtividadeEscolar {

	public FinderAtividadeEscolarImpl() {
		super(AtividadeEscolar.class);
	}

	public AtividadeEscolar pesquisar(Long id) {
		AtividadeEscolar o = null;
		o = super.pesquisar(id);
		return o;
	}
	
	public List<AtividadeEscolar> pesquisarPorNomeComo(String nome) {
		List<AtividadeEscolar> objs = new ArrayList<AtividadeEscolar>();
		objs = super.pesquisarPorNomeComo(nome);
		return objs;
	}

	public List<AtividadeEscolar> listar() {
		List<AtividadeEscolar> objs = new ArrayList<AtividadeEscolar>();
		objs = super.listar();
		return objs;
	}

	public void preencher(AtividadeEscolar o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
