package br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.IFinderAreaDeEstudo;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.AreaDeEstudo;

@Component
public class FinderAreaDeEstudoImpl extends Finder<AreaDeEstudo> implements IFinderAreaDeEstudo {

	public FinderAreaDeEstudoImpl() {
		super(AreaDeEstudo.class);
	}

	public AreaDeEstudo pesquisar(Long id) {
		AreaDeEstudo o = null;
		o = super.pesquisar(id);
		return o;
	}
	
	public List<AreaDeEstudo> pesquisarPorNomeComo(String nome) {
		List<AreaDeEstudo> objs = new ArrayList<AreaDeEstudo>();
		objs = super.pesquisarPorNomeComo(nome);
		return objs;
	}

	public List<AreaDeEstudo> listar() {
		List<AreaDeEstudo> objs = new ArrayList<AreaDeEstudo>();
		objs = super.listar();
		return objs;
	}

	public void preencher(AreaDeEstudo o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
