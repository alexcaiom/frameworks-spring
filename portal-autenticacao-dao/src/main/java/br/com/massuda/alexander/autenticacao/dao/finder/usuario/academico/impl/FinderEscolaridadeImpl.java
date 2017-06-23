package br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.IFinderEscolaridade;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.Escolaridade;

@Component
public class FinderEscolaridadeImpl extends Finder<Escolaridade> implements IFinderEscolaridade {

	public FinderEscolaridadeImpl() {
		super(Escolaridade.class);
	}

	public Escolaridade pesquisar(Long id) {
		Escolaridade o = null;
		o = super.pesquisar(id);
		return o;
	}
	
	public List<Escolaridade> pesquisarPorNomeComo(String nome) {
		List<Escolaridade> objs = new ArrayList<Escolaridade>();
		objs = super.pesquisarPorNomeComo(nome);
		return objs;
	}

	public List<Escolaridade> listar() {
		List<Escolaridade> objs = new ArrayList<Escolaridade>();
		objs = super.listar();
		return objs;
	}

	public void preencher(Escolaridade o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
