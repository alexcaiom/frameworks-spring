package br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.finder.impl.Finder;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.profissional.IFinderCargo;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Cargo;


@Component
public class FinderCargoImpl extends Finder<Cargo> implements IFinderCargo {

	public FinderCargoImpl() {
		super(Cargo.class);
	}

	public Cargo pesquisar(Long id) {
		Cargo c = null;
		c = super.pesquisar(id);
		return c;
	}
	
	public List<Cargo> pesquisarPorNomeComo(String nome) {
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos = super.pesquisarPorNomeComo(nome);
		return cargos;
	}

	public List<Cargo> listar() {
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos = super.listar();
		return cargos;
	}

	public void preencher(Cargo o) throws SQLException {
		if (existe(o) && existe(resultados) && !resultados.isClosed()) {
			super.preencher(o);
		}
	}

}
