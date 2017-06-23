package br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.profissional.IFinderSegmento;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Segmento;

@Component
public class FinderSegmentoImpl extends Finder<Segmento> implements IFinderSegmento {

	public FinderSegmentoImpl() {
		super(Segmento.class);
	}

	public Segmento pesquisar(Long id) {
		Segmento o = null;
		o = super.pesquisar(id);
		return o;
	}
	
	public List<Segmento> pesquisarPorNomeComo(String nome) {
		List<Segmento> objs = new ArrayList<Segmento>();
		objs = super.pesquisarPorNomeComo(nome);
		return objs;
	}

	public List<Segmento> listar() {
		List<Segmento> objs = new ArrayList<Segmento>();
		objs = super.listar();
		return objs;
	}

	public void preencher(Segmento o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
