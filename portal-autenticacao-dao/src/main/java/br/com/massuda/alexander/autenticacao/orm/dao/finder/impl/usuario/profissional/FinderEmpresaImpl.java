package br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.profissional.IFinderEmpresa;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Empresa;

@Component
public class FinderEmpresaImpl extends Finder<Empresa> implements IFinderEmpresa {

	public FinderEmpresaImpl() {
		super(Empresa.class);
	}

	public Empresa pesquisar(Long id) {
		Empresa s = null;
		s = super.pesquisar(id);
		return s;
	}
	
	public List<Empresa> pesquisarPorNomeComo(String nome) {
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = super.pesquisarPorNomeComo(nome);
		return empresas;
	}

	public List<Empresa> listar() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = super.listar();
		return empresas;
	}

	public void preencher(Empresa o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
