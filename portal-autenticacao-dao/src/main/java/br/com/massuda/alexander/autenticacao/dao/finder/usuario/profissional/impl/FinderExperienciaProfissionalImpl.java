package br.com.massuda.alexander.autenticacao.dao.finder.usuario.profissional.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.profissional.IFinderExperienciaProfissional;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.ExperienciaProfissional;

@Component
public class FinderExperienciaProfissionalImpl extends Finder<ExperienciaProfissional> implements IFinderExperienciaProfissional {

	public FinderExperienciaProfissionalImpl() {
		super(ExperienciaProfissional.class);
	}

	public ExperienciaProfissional pesquisar(Long id) {
		ExperienciaProfissional s = null;
		s = super.pesquisar(id);
		return s;
	}
	
	public List<ExperienciaProfissional> listar() {
		List<ExperienciaProfissional> empresas = new ArrayList<ExperienciaProfissional>();
		empresas = super.listar();
		return empresas;
	}

	public void preencher(ExperienciaProfissional o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
